package ee.aleksei.gvozdev.web.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class WageCreateDto {

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String surname;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1000000")
    @Digits(integer = 7, fraction = 2)
    private BigDecimal wage;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date eventTime;

    public String getName() {
        return name;
    }

    public WageCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public WageCreateDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public WageCreateDto setWage(BigDecimal wage) {
        this.wage = wage;
        return this;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public WageCreateDto setEventTime(Date eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    @Override
    public String toString() {
        return "WageCreateDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", wage=" + wage +
                ", eventTime=" + eventTime +
                '}';
    }
}
