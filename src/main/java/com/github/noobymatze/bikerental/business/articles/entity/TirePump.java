package com.github.noobymatze.bikerental.business.articles.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a tire pump for different Bikes.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "tirepump")
public class TirePump extends Product {

    @ManyToOne(optional = false)
    private Manufacturer manufacturer;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

}
