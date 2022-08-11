package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
