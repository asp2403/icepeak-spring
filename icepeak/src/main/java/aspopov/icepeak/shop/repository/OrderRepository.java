package aspopov.icepeak.shop.repository;

import aspopov.icepeak.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
