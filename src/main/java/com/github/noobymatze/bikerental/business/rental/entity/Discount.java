package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.products.entity.Product;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(
    name = "discount",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"time_period_id", "product_id"})
    }
)
@Getter
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin("1.00")
    @DecimalMax("50.00")
    private double percentage = 0.0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "time_period_id")
    private TimePeriod timePeriod;

    @ManyToOne(optional = false)
    private Product product;

    @Override
    public String toString() {
        DecimalFormat two = new DecimalFormat("#.##");
        return String.format(
            "%s%% on %s %s",
            two.format(percentage), product, timePeriod
        );
    }

}
