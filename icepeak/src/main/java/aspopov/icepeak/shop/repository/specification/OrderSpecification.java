package aspopov.icepeak.shop.repository.specification;

import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class OrderSpecification {

    public static Specification<Order> build(OrderSearchParams orderSearchParams) {
        return

                where(state(orderSearchParams.getState()))
                ;
    }

    private static Specification<Order> state(Integer state) {
        if (state == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("state"), state);
    }
}
