package com.github.noobymatze.bikerental.business.articles.entity;

import com.github.noobymatze.bikerental.business.rental.entity.Reservation;
import java.io.Serializable;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
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

    @OneToOne(orphanRemoval = true, optional = false, cascade = {MERGE, PERSIST, REFRESH})
    private Price pricePerMinute;

    @ManyToOne
    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Price getPricePerMinute() {
        return pricePerMinute;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean isAvailable() {
        return reservation == null;
    }

}
