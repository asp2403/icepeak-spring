package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import java.util.List;

public interface ModelService {
    List<Model> search(ModelSearchParams searchParams);
}
