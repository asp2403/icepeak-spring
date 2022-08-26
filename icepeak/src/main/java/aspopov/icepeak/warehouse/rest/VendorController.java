package aspopov.icepeak.warehouse.rest;

import aspopov.icepeak.warehouse.domain.Vendor;
import aspopov.icepeak.warehouse.service.VendorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/api/public/vendors")
    public List<Vendor> findAllOrderByName() {
        return vendorService.findAllOrderByName();
    }
}
