package ee.aleksei.gvozdev.service;

import ee.aleksei.gvozdev.kafka.wage.consumer.WageProcessor;
import ee.aleksei.gvozdev.kafka.api.WageEvent;
import ee.aleksei.gvozdev.persistence.domain.EmployeePo;
import ee.aleksei.gvozdev.persistence.repository.EmployeeRepository;
import ee.aleksei.gvozdev.wage.WageCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class EmployeeService implements WageProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final EmployeeRepository employeeRepository;
    private final WageCalculator wageCalculator;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            WageCalculator wageCalculator) {
        this.employeeRepository = employeeRepository;
        this.wageCalculator = wageCalculator;
    }

    @Override
    public void process(WageEvent wageEvent) {
        EmployeePo employeePo = employeeRepository
                .findByNameAndSurname(wageEvent.getName(), wageEvent.getSurname())
                .orElse(mapEmployee(wageEvent));
        BigDecimal wage = wageCalculator.calculateBruttoWage(wageEvent.getWage());
        employeePo.setWage(wage);
        employeePo.setLastEventTime(Instant.ofEpochMilli(wageEvent.getEventTimeMillis()));
        EmployeePo saved = employeeRepository.save(employeePo);
        log.info("Saved employee with wage: " + saved);
    }

    private EmployeePo mapEmployee(WageEvent wageEvent) {
        return new EmployeePo()
                .setName(wageEvent.getName())
                .setSurname(wageEvent.getSurname());
    }
}
