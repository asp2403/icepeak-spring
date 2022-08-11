package aspopov.icepeak.security.repository;

import aspopov.icepeak.security.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
