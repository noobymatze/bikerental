package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.rental.entity.Booking;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

/**
 * Provides methods for manipulating and accessing customers 
 * of this company.
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

    /**
     * Returns all customers stored in the database. 
     * 
     * <p>
     * <b>Note:</b> This should exclusively be used for testing.
     * </p>
     * 
     * @return List of all customers.
     */
    public List<Customer> getAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    /**
     * Creates a valid with the given items.
     * 
     * @param items
     * @param duration
     * @return 
     */
    public Booking book(Customer customer, List<Item> items, Duration duration) {
        Booking booking = new Booking();

        return booking;
    }

}
