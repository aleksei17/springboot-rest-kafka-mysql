package ee.aleksei.gvozdev.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity(name = "Employee")
@Table(
        name = "Employee",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "surname"})
)
public class EmployeePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "surname", length = 100, nullable = false)
    private String surname;

    @Column(name = "wage", nullable = false)
    private BigDecimal wage;

    @Column(name = "last_event_time", nullable = false)
    private Instant lastEventTime;

    public Long getId() {
        return id;
    }

    public EmployeePo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeePo setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EmployeePo setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public EmployeePo setWage(BigDecimal wage) {
        this.wage = wage;
        return this;
    }

    public Instant getLastEventTime() {
        return lastEventTime;
    }

    public EmployeePo setLastEventTime(Instant lastEventTime) {
        this.lastEventTime = lastEventTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePo that = (EmployeePo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EmployeePo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", wage=" + wage +
                ", lastEventTime=" + lastEventTime +
                '}';
    }
}
