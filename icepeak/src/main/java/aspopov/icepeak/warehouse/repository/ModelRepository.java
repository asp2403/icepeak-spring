package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.repository.projection.ModelShortView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {

    @EntityGraph(attributePaths = "vendor")
    Page<Model> findAll(Specification<Model> spec, Pageable pageable);

    List<ModelShortView> findByIdIn(List<Long> ids);

    Optional<ModelShortView> findModelShortById(long id);
}
