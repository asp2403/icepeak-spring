package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderTitleDto;

public interface BpmService {
    BpmData getActions(OrderTitleDto orderDto, long idManager);
    Order assignManager(long idOrder, long idManager);
    Order completeProcessing(long idOrder);
    Order returnToProcessing(long idOrder);
    Order completeDelivery(long idOrder);
}
