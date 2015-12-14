package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.articles.entity.Product;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents the reservation for a product for a specific period of
 * time.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    private boolean cancelled;

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "time_period_id")
    private TimePeriod timePeriod;

    @OneToMany(mappedBy = "reservation")
    private final List<Product> products = new ArrayList<>();

    @ManyToMany
    private final List<Discount> discounts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public String toString() {
        return String.format(
            "%s are reserved %s with discounts %s",
            products, timePeriod, discounts
        );
    }

}
