package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.ModelShortDto;
import aspopov.icepeak.warehouse.dto.SkiDto;
import aspopov.icepeak.warehouse.dto.SkiSearchParams;
import aspopov.icepeak.warehouse.service.SkiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class SkiController {
    private final SkiService skiService;

    public SkiController(SkiService skiService) {
        this.skiService = skiService;
    }

    @GetMapping("/api/ski/search")
    public Collection<ModelShortDto> search(SkiSearchParams skiSearchParams) {
        var modelDtos = skiService.search(skiSearchParams);
        return modelDtos;
    }

}
