package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.projection.SkiModelGalleryProjection;
import aspopov.icepeak.warehouse.service.SkiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkiController {
    private final SkiService skiService;

    public SkiController(SkiService skiService) {
        this.skiService = skiService;
    }

    @GetMapping("/api/ski/gallery")
    List<SkiModelGalleryProjection> findAllForGallery() {
        return skiService.findAllForGallery();
    }
}
