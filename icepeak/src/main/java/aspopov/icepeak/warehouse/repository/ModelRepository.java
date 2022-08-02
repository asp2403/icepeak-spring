package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {

}
