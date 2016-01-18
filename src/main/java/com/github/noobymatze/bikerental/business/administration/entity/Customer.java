package com.github.noobymatze.bikerental.business.administration.entity;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Represents an administrator of the rental company. 
 * They are able to administer articles and users.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public class Customer extends Person {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private ZonedDateTime createdAt;

    private ZonedDateTime deletedAt;

    @OneToMany(mappedBy = "customer")
    private final List<RentalDetails> rentals = new ArrayList<>();

    @OneToMany(cascade = {PERSIST, MERGE, REFRESH}, mappedBy = "customer")
    private final List<CustomerAddress> addresses = new ArrayList<>();

    /**
     * Add a new address. If it is the first, it will be the main address.
     * 
     * @param address 
     */
    public void addAddress(Address address) {
        CustomerAddress caddress = CustomerAddress.withCustomerAndAddress(
            this,
            address,
            addresses.isEmpty()
        );

        this.addresses.add(caddress);
    }

    @Override
    public String toString() {
        return getEmail();
    }
    
}
