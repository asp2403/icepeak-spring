package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.dto.OrderDto;

public interface BpmService {
    BpmData getActions(OrderDto orderDto, long idManager);
}
