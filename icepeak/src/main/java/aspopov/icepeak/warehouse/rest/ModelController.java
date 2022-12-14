package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.dto.ModelFullDto;
import aspopov.icepeak.warehouse.dto.ModelShortDto;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import aspopov.icepeak.warehouse.service.ModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/api/public/models/search")
    Page<ModelShortDto> search(ModelSearchParams searchParams, Pageable pageable) {
        var models = modelService.search(searchParams, pageable);
        var modelsDto = models.map(ModelShortDto::fromDomain);
        return modelsDto;
    }

    @GetMapping("/api/public/models/{id}")
    ResponseEntity<ModelFullDto> getModel(@PathVariable long id) {
        return ResponseEntity.of(modelService.findById(id).map(ModelFullDto::fromDomain));
    }

    @GetMapping("/api/public/models/short/{id}")
    ResponseEntity<ModelShortDto> getModelShort(@PathVariable long id) {
        return ResponseEntity.of(modelService.findModelShortById(id).map(ModelShortDto::fromView));
    }

    @GetMapping("/api/public/models/short/list")
    List<ModelShortDto> findModels(Long[] ids) {
        var models = modelService.findByModelIdIn(Arrays.asList(ids));
        return models.stream().map(ModelShortDto::fromView).collect(Collectors.toList());
    }

}
