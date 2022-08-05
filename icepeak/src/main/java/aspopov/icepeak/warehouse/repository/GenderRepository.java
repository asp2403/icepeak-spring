package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    List<Gender> findByOrderByValue();
}
