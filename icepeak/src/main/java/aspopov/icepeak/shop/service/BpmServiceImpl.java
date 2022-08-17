package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.BpmAction;
import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.exception.WrongOrderStateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BpmServiceImpl implements BpmService {

    @Override
    public BpmData getActions(int state) {
        switch (state) {
            case OrderState.NEW:
                return new BpmData(null, BpmAction.ASSIGN);
            case OrderState.PROCESSING:
                return new BpmData(BpmAction.ASSIGN, BpmAction.COMPLETE_PROCESSING);
            case OrderState.READY:
                return new BpmData(BpmAction.RETURN_TO_PROCESSING, BpmAction.DELIVER);
            case OrderState.DELIVERED:
            case OrderState.CANCELLED:
                return new BpmData(null, null);
            default:
                throw new WrongOrderStateException(state);
        }
    }
}
