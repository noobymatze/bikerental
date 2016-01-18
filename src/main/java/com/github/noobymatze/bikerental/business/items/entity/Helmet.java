package com.github.noobymatze.bikerental.business.items.entity;

import javax.persistence.Entity;
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
public class Helmet extends Item {

    private String model;

}
