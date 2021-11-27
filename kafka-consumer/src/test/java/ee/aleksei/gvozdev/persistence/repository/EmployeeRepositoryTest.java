package ee.aleksei.gvozdev.persistence.repository;

import ee.aleksei.gvozdev.persistence.domain.EmployeePo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void save() {
        EmployeePo saved = employeeRepository.save(buildEmployee());

        assertNotNull(saved.getId());
    }

    @Test
    void testNameAndSurnameAreUnique() {
        employeeRepository.save(buildEmployee());

        assertThrows(DataIntegrityViolationException.class, () -> employeeRepository.save(buildEmployee()));
    }

    @Test
    void findByNameAndSurname() {
        assertTrue(employeeRepository.findByNameAndSurname("name", "surname").isEmpty());
        employeeRepository.save(buildEmployee());

        assertTrue(employeeRepository.findByNameAndSurname("name", "surname").isPresent());
    }

    private EmployeePo buildEmployee() {
        return new EmployeePo().setName("name").setSurname("surname").setWage(BigDecimal.ONE).setLastEventTime(Instant.now());
    }
}
