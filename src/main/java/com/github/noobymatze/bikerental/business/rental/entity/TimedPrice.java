package com.github.noobymatze.bikerental.business.rental.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents the price during a specified period of time.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "timed_price")
public class TimedPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private TimePeriod timePeriod;

    private BigDecimal euros;

    public Long getId() {
        return id;
    }

    public BigDecimal getEuros() {
        return euros;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    @Override
    public String toString() {
        return String.format(
            "%s with %s €",
            timePeriod, euros
        );
    }

}
