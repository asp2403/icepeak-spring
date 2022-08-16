package aspopov.icepeak.shop.repository;

import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.warehouse.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAll(Specification<Order> spec, Pageable pageable);

}
