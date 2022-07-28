package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
