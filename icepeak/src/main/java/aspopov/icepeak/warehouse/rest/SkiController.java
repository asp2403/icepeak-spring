package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.dto.ModelShortDto;
import aspopov.icepeak.warehouse.dto.SearchParams;
import aspopov.icepeak.warehouse.service.ModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SkiController {
    private final ModelService modelService;

    public SkiController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/api/ski/search")
    List<ModelShortDto> search(SearchParams searchParams) {
        var models = modelService.search(searchParams);
        var modelsDto = models.stream().map(ModelShortDto::fromDomain).collect(Collectors.toList());
        return modelsDto;
    }
}
