package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Age;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgeRepository extends JpaRepository<Age, Long> {
    List<Age> findByOrderByValue();
}
