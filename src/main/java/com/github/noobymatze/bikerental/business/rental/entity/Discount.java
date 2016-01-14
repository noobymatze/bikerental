package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import lombok.Getter;

/**
 * Represents a discount for the specified product during the given time
 * period. 
 * 
 * <ul>
 * <li>Only one discount per specified time period per product is allowed.</li>
 * <li>The percentage should not be greater than 50%.</li>
 * </ul>
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "discount")
@Getter
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin("1.00")
    @DecimalMax("50.00")
    private double percentage = 0.0;

    @Embedded
    private Duration timePeriod;

    @Override
    public String toString() {
        DecimalFormat two = new DecimalFormat("#.##");
        return String.format(
            "%s",
            two.format(percentage)
        );
    }

}
