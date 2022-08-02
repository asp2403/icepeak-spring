package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.dto.SearchParams;
import aspopov.icepeak.warehouse.repository.ModelRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static aspopov.icepeak.warehouse.repository.ModelSpecification.nameLike;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> search(SearchParams searchParams) {
        var spec = Specification.where(nameLike(searchParams.getModelName()));
        return modelRepository.findAll(spec);
    }
}
