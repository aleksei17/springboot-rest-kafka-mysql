package ee.aleksei.gvozdev.persistence.repository;

import ee.aleksei.gvozdev.persistence.domain.EmployeePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeePo, Long> {

    Optional<EmployeePo> findByNameAndSurname(String name, String surname);
}
