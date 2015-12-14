package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.products.entity.Product;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.Column;
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

/**
 * Represents a discount for the specified product during the given time
 * period. 
 * 
 * <h4>Invariants</h4>
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        DecimalFormat two = new DecimalFormat("#.##");
        return String.format(
            "%s%% on %s %s",
            two.format(percentage), product, timePeriod
        );
    }

}
