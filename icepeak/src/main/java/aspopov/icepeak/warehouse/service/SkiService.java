package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.projection.SkiModelGalleryProjection;

import java.util.List;

public interface SkiService {
    List<SkiModelGalleryProjection> findAllForGallery();
}
