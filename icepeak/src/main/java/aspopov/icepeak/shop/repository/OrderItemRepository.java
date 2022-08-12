package aspopov.icepeak.shop.repository;

import aspopov.icepeak.shop.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
