package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
