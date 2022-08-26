package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.domain.Gender;
import aspopov.icepeak.warehouse.service.GenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenderController {
    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/api/public/genders")
    public List<Gender> findAll() {
        return genderService.findAll();
    }
}
