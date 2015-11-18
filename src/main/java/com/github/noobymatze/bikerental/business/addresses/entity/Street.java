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
    
    /**
     * Create a new street in the given city specified by the zipcode.
     * 
     * @param name The name of the street.
     * @param zipcode The zipcode of the city, where the street can be found..
     * @return Fresh street with the name and zipcode.
     */
    public static Street withNameAndZip(String name, Zipcode zipcode) {
        Street street = new Street();
        street.name = name;
        street.zipcode = zipcode;
        return street;
    }

}
