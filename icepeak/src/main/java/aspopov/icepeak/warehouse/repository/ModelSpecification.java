package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Boots;
import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class ModelSpecification {

    public static Specification<Model> build(ModelSearchParams modelSearchParams) {
        return

                where(heightFrom(modelSearchParams.getHeightFrom()))
                        .and(heightTo(modelSearchParams.getHeightTo()))
                        .and(nameLike(modelSearchParams.getModelName()))
                        .and(vendorNameLike(modelSearchParams.getVendorName()))
                        .and(gender(modelSearchParams.getIdGender()))
                        .and(age(modelSearchParams.getIdAge()))
                        .and(priceFrom(modelSearchParams.getPriceFrom()))
                        .and(priceTo(modelSearchParams.getPriceTo()))
                        .and(sizeFrom(modelSearchParams.getSizeFrom()))
                        .and(sizeTo(modelSearchParams.getSizeTo()))
                        .and(category(modelSearchParams.getCategory()))
                ;
    }

    private static Specification<Model> category(Short category) {
        if (category == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get("category"), category);
    }

    private static Specification<Model> nameLike(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Model> priceFrom(Integer price) {
        if (price == null) {
            return null;
        }
        return (root, query, cb) -> cb.ge(root.get("price"), price);
    }

    private static Specification<Model> priceTo(Integer price) {
        if (price == null) {
            return null;
        }
        return (root, query, cb) -> cb.le(root.get("price"), price);
    }

    private static Specification<Model> heightFrom(Integer height) {
        if (height == null) {
            return null;
        }
        return (root, query, cb) -> {
            query.distinct(true);
            var product = root.join("products");
            var ski = cb.treat(product, Ski.class);
            return cb.ge(ski.get("height"), height);
        };
    }

    private static Specification<Model> heightTo(Integer height) {
        if (height == null) {
            return null;
        }
        return (root, query, cb) -> {
            query.distinct(true);
            var productJoin = root.join("products");
            var ski = cb.treat(productJoin, Ski.class);
            return cb.le(ski.get("height"), height);
        };
    }

    private static Specification<Model> sizeFrom(Float size) {
        if (size == null) {
            return null;
        }
        return (root, query, cb) -> {
            query.distinct(true);
            var productJoin = root.join("products");
            var boots = cb.treat(productJoin, Boots.class);
            return cb.ge(boots.get("size"), size);
        };
    }

    private static Specification<Model> sizeTo(Float size) {
        if (size == null) {
            return null;
        }
        return (root, query, cb) -> {
            query.distinct(true);
            var productJoin = root.join("products");
            var boots = cb.treat(productJoin, Boots.class);
            return cb.le(boots.get("size"), size);
        };
    }

    private static Specification<Model> vendorNameLike(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> {
            var vendorJoin = root.join("vendor");
            return cb.like(cb.lower(vendorJoin.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    private static Specification<Model> gender(Long idGender) {
        if (idGender == null) {
            return null;
        }
        return (root, query, cb) -> {
            var genderJoin = root.join("gender");
            return cb.equal(genderJoin.get("id"), idGender);
        };
    }

    private static Specification<Model> age(Long idAge) {
        if (idAge == null) {
            return null;
        }
        return (root, query, cb) -> {
            var ageJoin = root.join("age");
            return cb.equal(ageJoin.get("id"), idAge);
        };
    }


}
