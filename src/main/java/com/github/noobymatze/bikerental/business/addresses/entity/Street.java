package com.github.noobymatze.bikerental.business.addresses.entity;

import java.io.Serializable;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a street in a city.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "street")
public class Street implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    private Zipcode zipcode;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Zipcode getZipcode() {
        return zipcode;
    }

    @Override
    public String toString() {
        return String.format(
            "%s, %s",
            getName(), getZipcode()
        );
    }
    
}
