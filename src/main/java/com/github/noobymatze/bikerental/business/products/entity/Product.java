package com.github.noobymatze.bikerental.business.products.entity;

import com.github.noobymatze.bikerental.business.rental.entity.Reservation;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents an article in the bike rental.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "purchase_date")
    private ZonedDateTime purchaseDate;

    @OneToOne(orphanRemoval = true, optional = false, cascade = {MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "price_per_minute")
    private Price pricePerMinute;

    @ManyToMany
    private final List<Reservation> reservations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public ZonedDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public String getDescription() {
        return description;
    }

    public Price getPricePerMinute() {
        return pricePerMinute;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
