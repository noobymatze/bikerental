package com.github.noobymatze.bikerental.business.addresses.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a country of the earth.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    /**
     * Create a new country with the given name.
     * 
     * @param name The name of the country.
     * @return Fresh country instance.
     */
    public static Country withName(String name) {
        Country c = new Country();
        c.name = name;
        return c;
    }

}
