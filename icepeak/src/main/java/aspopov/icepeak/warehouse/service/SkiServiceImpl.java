package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.projection.SkiModelGalleryProjection;
import aspopov.icepeak.warehouse.repository.SkiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkiServiceImpl implements SkiService {
    private final SkiRepository skiRepository;

    public SkiServiceImpl(SkiRepository skiRepository) {
        this.skiRepository = skiRepository;
    }

    @Override
    public List<SkiModelGalleryProjection> findAllForGallery() {
        return skiRepository.findAllSkiForGallery();
    }
}
