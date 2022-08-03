package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Ski;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SkiRepository extends JpaRepository<Ski, Long>, JpaSpecificationExecutor<Ski> {

}
