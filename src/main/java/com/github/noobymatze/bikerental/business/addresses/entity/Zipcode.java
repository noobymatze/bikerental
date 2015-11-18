package com.github.noobymatze.bikerental.business.addresses.entity;

import java.io.Serializable;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a zipcode of a city.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "zipcode")
public class Zipcode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "val")
    private String value;

    @ManyToOne(optional = false, cascade = {PERSIST, REFRESH, MERGE})
    private City city;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s", 
            getValue(), getCity()
        );
    }

    /**
     * Create a new zipcode of the given city.
     * 
     * @param value The code.
     * @param city The city to which this zipcode belongs.
     * @return Fresh zipcode for the given city.
     */
    public static Zipcode withCity(String value, City city) {
        Zipcode zipcode = new Zipcode();
        zipcode.value = value;
        zipcode.city = city;
        return zipcode;
    }

}
