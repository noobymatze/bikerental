package com.github.noobymatze.bikerental.business.products.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents a tire pump for different Bikes.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "tirepump")
@Getter
public class TirePump extends Product {

    @ManyToOne(optional = false)
    private Manufacturer manufacturer;

}
