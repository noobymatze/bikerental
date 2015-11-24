package com.github.noobymatze.bikerental.business.rental.entity;

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
    private final List<Article> articles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public List<Article> getArticles() {
        return articles;
    }

}
