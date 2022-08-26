package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Model;

import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import aspopov.icepeak.warehouse.repository.ModelRepository;

import aspopov.icepeak.warehouse.repository.specification.ModelSpecification;
import aspopov.icepeak.warehouse.repository.projection.ModelShortView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Model> search(ModelSearchParams searchParams, Pageable pageable) {
        var spec = ModelSpecification.build(searchParams);
        return modelRepository.findAll(spec, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> search(ModelSearchParams searchParams) {
        var spec = ModelSpecification.build(searchParams);
        return modelRepository.findAll(spec);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Model> findById(long id) {
        return modelRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelShortView> findByModelIdIn(List<Long> ids) {
        return modelRepository.findByIdIn(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ModelShortView> findModelShortById(long id) {
        return modelRepository.findModelShortById(id);
    }
}
