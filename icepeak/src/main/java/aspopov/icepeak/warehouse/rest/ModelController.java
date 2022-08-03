package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.dto.ModelShortDto;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import aspopov.icepeak.warehouse.service.ModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/api/model/search")
    List<ModelShortDto> search(ModelSearchParams searchParams) {
        var models = modelService.search(searchParams);
        var modelsDto = models.stream().map(ModelShortDto::fromDomain).collect(Collectors.toList());
        return modelsDto;
    }
}
