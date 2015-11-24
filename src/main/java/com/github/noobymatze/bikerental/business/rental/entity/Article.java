package com.github.noobymatze.bikerental.business.rental.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents an article in the bike rental.
 *
 * @author Matthias Metzger
 */
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(orphanRemoval = true, optional = false, cascade = {MERGE, PERSIST, REFRESH})
    private TimedPrice pricePerHour;

    @ManyToOne
    private Reservation reservation;

    @ManyToMany(mappedBy = "articles")
    private final List<Category> categories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TimedPrice getPricePerHour() {
        return pricePerHour;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean isAvailable() {
        return reservation == null;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

}
