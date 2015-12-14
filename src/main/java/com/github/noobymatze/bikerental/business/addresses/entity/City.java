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
import lombok.Getter;

/**
 * Represents a city in a country.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "city")
@Getter
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(optional = false, cascade = {PERSIST, REFRESH, MERGE})
    private Country country;

    @Override
    public String toString() {
        return String.format(
            "%s in %s",
            getName(), getCountry()
        );
    }
    
    /**
     * Create a new city in a given country.
     * 
     * @param name The name of the city.
     * @param country The country in which the city is.
     * @return Fresh city within a country.
     */
    public static City withNameIn(String name, Country country) {
        City city = new City();
        city.name = name;
        city.country = country;
        return city; 
    }

}
