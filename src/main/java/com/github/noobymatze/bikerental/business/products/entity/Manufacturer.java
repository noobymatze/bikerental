package com.github.noobymatze.bikerental.business.products.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents a manufacturer. 
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "manufacturer")
@Getter
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public String toString() {
        return getName();
    }

}
