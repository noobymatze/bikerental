package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.products.entity.Product;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents the reservation for a product for a specific period of
 * time.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "reservation")
@Getter
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

    @ManyToMany(mappedBy = "reservation")
    @JoinTable(name = "reservation_has_product")
    private final List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "reservation_has_discount")
    private final List<Discount> discounts = new ArrayList<>();

    @ManyToOne(optional = false)
    private Customer customer;

    @Override
    public String toString() {
        return String.format(
            "%s reserved %s %s with discounts %s",
            customer, products, timePeriod, discounts
        );
    }

}
