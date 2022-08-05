package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.domain.Age;
import aspopov.icepeak.warehouse.service.AgeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgeController {
    private final AgeService ageService;

    public AgeController(AgeService ageService) {
        this.ageService = ageService;
    }

    @GetMapping("/api/ages")
    public List<Age> findAll() {
        return ageService.findAll();
    }
}
