package com.github.noobymatze.bikerental.business.items.entity;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents a manufacturer. 
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "company")
@Getter
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private final List<Address> addresses = new ArrayList<>();

    @Override
    public String toString() {
        return getName();
    }

}
