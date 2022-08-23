package aspopov.icepeak.shop.service;

import aspopov.icepeak.config.AppProperties;
import aspopov.icepeak.shop.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class OrderAutoCancelService {

    private final OrderRepository orderRepository;
    private final AppProperties appProperties;
    private final OrderService orderService;

    private static final Logger log =
            LoggerFactory.getLogger(OrderAutoCancelService.class);

    public OrderAutoCancelService(OrderRepository orderRepository, AppProperties appProperties, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.appProperties = appProperties;
        this.orderService = orderService;
    }

    @Transactional
    @Scheduled(initialDelay = 1000, fixedRateString= "${app.cancel-task-rate}")
    public void cancelOldOrders() {
        log.info("Отмена просроченных заказов");
        var oldOrders = orderRepository.findOldOrders(appProperties.getCancelOldOrdersInterval());
        oldOrders.forEach(order -> orderService.cancelOrder(order));
    }

}
