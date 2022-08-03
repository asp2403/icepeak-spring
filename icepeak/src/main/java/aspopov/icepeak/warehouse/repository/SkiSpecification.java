package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.SkiSearchParams;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class SkiSpecification {

    public static Specification<Ski> build(SkiSearchParams skiSearchParams) {
        return where(
                heightFrom(skiSearchParams.getHeightFrom()))
                .and(heightTo(skiSearchParams.getHeightTo())
//                        .and(modelNameLike(skiSearchParams.getModelName()))
                );
    }

    private static Specification<Ski> heightFrom(Integer height) {
        if (height == null) {
            return null;
        }
        return (root, query, cb) -> cb.ge(root.get("height"), height);
    }

    private static Specification<Ski> heightTo(Integer height) {
        if (height == null) {
            return null;
        }
        return (root, query, cb) -> cb.le(root.get("height"), height);
    }

//    private static Specification<Ski> modelNameLike(String name) {
//        if (name == null) {
//            return null;
//        }
//        return (root, query, cb) ->
//                cb.like(cb.lower(root.get("model.name")), "%" + name.toLowerCase() + "%");
//    }


}
