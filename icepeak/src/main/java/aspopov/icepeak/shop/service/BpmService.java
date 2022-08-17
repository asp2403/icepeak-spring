package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.BpmData;

public interface BpmService {
    BpmData getActions(int state);
}
