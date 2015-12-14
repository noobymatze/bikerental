package com.github.noobymatze.bikerental.business.products.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

/**
 * A helmet for a bike.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "helmet")
@Getter
public class Helmet extends Product {

    private String model;

    @ManyToOne(optional = true)
    private Manufacturer manufacturer;

}
