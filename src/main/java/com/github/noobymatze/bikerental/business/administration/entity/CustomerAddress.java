package com.github.noobymatze.bikerental.business.administration.entity;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import java.io.Serializable;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Represents an address of a customer. It is purely used
 * to keep the address and its handling in its own package, as well
 * as modeling a m:n relationship between customer and addresses.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "customer_has_address")
public class CustomerAddress implements Serializable {

    @EmbeddedId
    private CustomerAddressId id;

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    @MapsId("address_id")
    private Address address;

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    @MapsId("customer_id")
    private Customer customer;

    public Address getAddress() {
        return address;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * Associate the given customer with the given address.
     * 
     * @param customer The customer, whose new or other address needs to be added.
     * @param address The address added to the customer.
     * @return A new CustomerAddress representing the address of the customer.
     */
    public static CustomerAddress withCustomerAndAddress(Customer customer, Address address) {
        CustomerAddress caddr = new CustomerAddress();
        caddr.address = address;
        caddr.customer = customer;
        caddr.id = CustomerAddressId.fromCustomerAndAddress(customer, address);
        return caddr;
    }
    
}
