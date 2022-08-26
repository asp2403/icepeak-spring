package aspopov.icepeak.shop.repository;

import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.warehouse.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAll(Specification<Order> spec, Pageable pageable);

    @Query(value = "select * from \"order\" " +
            "where state = 3 and round(extract(epoch from (now() - ready_date)) / 60) > :intervalInMinutes "
            , nativeQuery = true)
    List<Order> findOldOrders(@Param("intervalInMinutes") int intervalInMinutes);

}
