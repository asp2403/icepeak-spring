package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Gender;
import aspopov.icepeak.warehouse.repository.GenderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gender> findAll() {
        return genderRepository.findByOrderByValue();
    }
}
