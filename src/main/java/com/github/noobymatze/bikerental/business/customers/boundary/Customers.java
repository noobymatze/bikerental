package com.github.noobymatze.bikerental.business.customers.boundary;

import com.github.noobymatze.bikerental.business.customers.entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matthias Metzger
 */
@Stateless
public class Customers {

    @PersistenceContext(unitName = "bikerental")
    EntityManager em;

    /**
     * Persist or update a customer.
     * 
     * @param customer The customer instance to be saved.
     * @return A new instance, which contains the automatically
     * incremented id.
     */
    public Customer save(@NotNull Customer customer) {
        return em.merge(customer);
    }

}
