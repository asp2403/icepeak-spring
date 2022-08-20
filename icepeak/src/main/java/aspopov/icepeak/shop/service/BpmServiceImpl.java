package aspopov.icepeak.shop.service;

import aspopov.icepeak.security.repository.ManagerRepository;
import aspopov.icepeak.shop.domain.BpmAction;
import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.exception.ManagerNotAssignedException;
import aspopov.icepeak.shop.exception.ManagerNotFoundException;
import aspopov.icepeak.shop.exception.OrderNotFoundException;
import aspopov.icepeak.shop.exception.WrongOrderStateException;
import aspopov.icepeak.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class BpmServiceImpl implements BpmService {

    private final OrderRepository orderRepository;
    private final ManagerRepository managerRepository;
    private final ProductService productService;

    public BpmServiceImpl(OrderRepository orderRepository, ManagerRepository managerRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.managerRepository = managerRepository;
        this.productService = productService;
    }

    @Override
    public BpmData getActions(OrderDto orderDto, long idManager) {
        switch (orderDto.getState()) {
            case OrderState.NEW:
                return new BpmData(null, BpmAction.ASSIGN);
            case OrderState.PROCESSING:
                if (orderDto.getManager() == null) {
                    throw new ManagerNotAssignedException();
                }
                if (orderDto.getManager().getId() != idManager) {
                    return new BpmData(BpmAction.ASSIGN, null);
                }
                return new BpmData(null, BpmAction.COMPLETE_PROCESSING);
            case OrderState.READY:
                if (orderDto.getManager() == null) {
                    throw new ManagerNotAssignedException();
                }
                if (orderDto.getManager().getId() == idManager) {
                    return new BpmData(BpmAction.RETURN_TO_PROCESSING, BpmAction.COMPLETE_DELIVERY);
                } else {
                    return new BpmData(BpmAction.ASSIGN, null);
                }
            case OrderState.DELIVERED:
            case OrderState.CANCELLED:
                return new BpmData(null, null);
            default:
                throw new WrongOrderStateException(orderDto.getState());
        }
    }

    @Override
    @Transactional
    public Order completeProcessing(long idOrder) {
        var order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        if (order.getState() != OrderState.PROCESSING) {
            throw new WrongOrderStateException(order.getState());
        }
        order.setState(OrderState.READY);
        order.setReadyDate(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order returnToProcessing(long idOrder) {
        var order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        if (order.getState() != OrderState.READY) {
            throw new WrongOrderStateException(order.getState());
        }
        order.setState(OrderState.PROCESSING);
        order.setReadyDate(null);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order completeDelivery(long idOrder) {
        var order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        if (order.getState() != OrderState.READY) {
            throw new WrongOrderStateException(order.getState());
        }
        var orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> productService.completeDelivery(orderItem.getProduct(), orderItem.getQty()));
        order.setState(OrderState.DELIVERED);
        order.setFinalDate(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order assignManager(long idOrder, long idManager) {
        var order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        var manager = managerRepository.findById(idManager).orElseThrow(() -> new ManagerNotFoundException(idManager));
        order.setManager(manager);
        if (order.getState() != OrderState.READY) {
            order.setState(OrderState.PROCESSING);
        }
        if (order.getAssignDate() == null) {
            order.setAssignDate(new Timestamp(System.currentTimeMillis()));
        }
        return orderRepository.save(order);
    }
}
