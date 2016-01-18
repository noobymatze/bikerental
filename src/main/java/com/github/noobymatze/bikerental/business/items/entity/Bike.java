package com.github.noobymatze.bikerental.business.items.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents a Bike.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "bike")
@Getter
public class Bike extends Item {

    @Column(name = "frame_number", unique = true)
    private String frameNumber;

    private String model;

}
