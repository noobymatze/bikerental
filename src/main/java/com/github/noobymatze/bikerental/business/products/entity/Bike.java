package com.github.noobymatze.bikerental.business.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a Bike.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "bike")
public class Bike extends Product {

    @Column(name = "frame_number", unique = true)
    private String frameNumber;

    private String model;

    @ManyToOne(optional = false)
    private Manufacturer manufacturer;

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

}
