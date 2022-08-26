package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Vendor;
import aspopov.icepeak.warehouse.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vendor> findAllOrderByName() {
        return vendorRepository.findByOrderByName();
    }
}
