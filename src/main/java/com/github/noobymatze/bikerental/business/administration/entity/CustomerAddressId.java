package com.github.noobymatze.bikerental.business.administration.entity;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

/**
 *
 * @author Matthias Metzger
 */
public class CustomerAddressId implements Serializable {

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "customer_id")
    private Long customerId;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.addressId);
        hash = 41 * hash + Objects.hashCode(this.customerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerAddressId other = (CustomerAddressId) obj;
        if (!Objects.equals(this.addressId, other.addressId)) {
            return false;
        }
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        return true;
    }

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
    static CustomerAddressId fromCustomerAndAddress(Customer customer, Address address) {
        CustomerAddressId id = new CustomerAddressId();
        id.addressId = address.getId();
        id.customerId = customer.getId();
        return id;
    }

}
