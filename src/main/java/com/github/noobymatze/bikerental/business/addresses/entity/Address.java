package com.github.noobymatze.bikerental.business.addresses.entity;

import java.io.Serializable;
import java.util.Optional;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an address in the real world.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "house_number")
    private Integer houseNumber;

    private String addition;

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    private Street street;

    public Long getId() {
        return id;
    }

    public Street getStreet() {
        return street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getAddition() {
        return addition;
    }

    public Optional<String> getAdditionO() {
        return Optional.ofNullable(addition);
    }

    @Override
    public String toString() {
        String address = getAdditionO().
            map(val -> houseNumber + " " + val).
            orElse(houseNumber.toString());

        return String.format("%s, %s", address, street);
    }
    
    /**
     * Creates a new address with the given house number in
     * the specified street.
     * 
     * @param house The number of the house.
     * @param street The street in which this house can be found.
     * @return Fresh address.
     */
    public static Address withHouseAndZip(Integer house , Street street) {
        Address address = new Address();
        address.houseNumber = house;
        address.street = street;
        return address;
    }

}
