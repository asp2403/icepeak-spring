package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.BpmAction;
import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.exception.ManagerNotAssignedException;
import aspopov.icepeak.shop.exception.WrongOrderStateException;
import org.springframework.stereotype.Service;

@Service
public class BpmServiceImpl implements BpmService {

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
}
