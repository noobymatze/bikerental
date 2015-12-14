package com.github.noobymatze.bikerental.business.administration.entity;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import java.io.Serializable;
import javax.persistence.Column;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Matthias Metzger
 */
@EqualsAndHashCode
public class CustomerAddressId implements Serializable {

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "customer_id")
    private Long customerId;

    /**
     * Creates a new {@link CustomerAddressId} from the given Customer and
     * Address.
     * 
     * @param customer The customer, of which the id for this customer address id will
     * be used.
     * @param address The address, of which the id for this customer address id will
     * be used.
     * @return A new CustomerAddressId
     */
    static CustomerAddressId of(Customer customer, Address address) {
        CustomerAddressId id = new CustomerAddressId();
        id.addressId = address.getId();
        id.customerId = customer.getId();
        return id;
    }

}
