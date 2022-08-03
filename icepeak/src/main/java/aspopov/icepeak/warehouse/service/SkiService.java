package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.ModelShortDto;
import aspopov.icepeak.warehouse.dto.SkiDto;
import aspopov.icepeak.warehouse.dto.SkiSearchParams;

import java.util.Collection;
import java.util.List;

public interface SkiService {
    Collection<ModelShortDto> search(SkiSearchParams skiSearchParams);
}
