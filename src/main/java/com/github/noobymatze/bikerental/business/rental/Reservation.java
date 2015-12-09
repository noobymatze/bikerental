package com.github.noobymatze.bikerental.business.rental;

import com.github.noobymatze.bikerental.business.articles.entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents the reservation for an article for a specific period of
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

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    private TimePeriod timePeriod;

    @OneToMany(mappedBy = "reservation")
    private final List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public List<Product> getProducts() {
        return products;
    }

}
