package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> findAllOrderByName();
}
