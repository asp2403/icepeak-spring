package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModelService {
    Page<Model> search(ModelSearchParams searchParams, Pageable pageable);
    List<Model> search(ModelSearchParams searchParams);
}
