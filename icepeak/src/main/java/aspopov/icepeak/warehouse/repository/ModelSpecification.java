package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Model;
import org.springframework.data.jpa.domain.Specification;

public class ModelSpecification {
    public static Specification<Model> nameLike(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
