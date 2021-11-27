package ee.aleksei.gvozdev.kafka.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Objects;

public class WageEvent {

    private String name;

    private String surname;

    private BigDecimal wage;

    private Long eventTimeMillis;

    @JsonIgnore
    public String getKey() {
        return name + "-" + surname;
    }

    public String getName() {
        return name;
    }

    public WageEvent setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public WageEvent setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public WageEvent setWage(BigDecimal wage) {
        this.wage = wage;
        return this;
    }

    public Long getEventTimeMillis() {
        return eventTimeMillis;
    }

    public WageEvent setEventTimeMillis(Long eventTimeMillis) {
        this.eventTimeMillis = eventTimeMillis;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WageEvent wageEvent = (WageEvent) o;
        return Objects.equals(name, wageEvent.name) &&
                Objects.equals(surname, wageEvent.surname) &&
                Objects.equals(wage, wageEvent.wage) &&
                Objects.equals(eventTimeMillis, wageEvent.eventTimeMillis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, wage, eventTimeMillis);
    }

    @Override
    public String toString() {
        return "WageEvent{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", wage=" + wage
                + ", eventTimeMillis=" + eventTimeMillis
                + '}';
    }
}
