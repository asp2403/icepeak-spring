package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Age;
import aspopov.icepeak.warehouse.repository.AgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgeServiceImpl implements AgeService {
    private final AgeRepository ageRepository;

    public AgeServiceImpl(AgeRepository ageRepository) {
        this.ageRepository = ageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Age> findAll() {
        return ageRepository.findByOrderByValue();
    }
}
