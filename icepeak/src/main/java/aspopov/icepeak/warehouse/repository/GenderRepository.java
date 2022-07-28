package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {

}
