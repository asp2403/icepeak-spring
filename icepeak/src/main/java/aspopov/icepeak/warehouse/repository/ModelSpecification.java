package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import org.springframework.data.jpa.domain.Specification;

import static org.hibernate.criterion.Projections.distinct;
import static org.springframework.data.jpa.domain.Specification.where;

public class ModelSpecification {
    private static Specification<Model> nameLike(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Model> heightFrom(Integer height) {
        if (height == null) {
            return null;
        }
        return (root, query, cb) -> {
            var product = root.join("products");
            var ski = cb.treat(product, Ski.class);
            query.distinct(true);
            return cb.ge(ski.get("height"), height);
        };
    }

    private static Specification<Model> heightTo(Integer height) {
        if (height == null) {
            return null;
        }
        return (root, query, cb) -> cb.le(root.get("height"), height);
    }

    public static Specification<Model> build(ModelSearchParams modelSearchParams) {
        return

                where(heightFrom(modelSearchParams.getHeightFrom()))
//                        .and(heightTo(modelSearchParams.getHeightTo()))
                        .and(nameLike(modelSearchParams.getModelName()));
    }
}
