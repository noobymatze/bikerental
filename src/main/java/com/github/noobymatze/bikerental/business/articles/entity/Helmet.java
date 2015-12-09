package com.github.noobymatze.bikerental.business.articles.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A helmet for a bike.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "helmet")
public class Helmet extends Product {

    private String model;

    @ManyToOne(optional = true)
    private Manufacturer manufacturer;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

}
