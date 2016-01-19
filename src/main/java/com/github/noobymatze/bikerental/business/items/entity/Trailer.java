package com.github.noobymatze.bikerental.business.items.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "trailer")
@Getter
@Setter
public class Trailer extends Item {

    private boolean roof = false;

    private int seats = 2;
    
}
