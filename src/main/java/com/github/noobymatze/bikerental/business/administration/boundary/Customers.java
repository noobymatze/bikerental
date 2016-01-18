package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.items.boundary.Items;
import com.github.noobymatze.bikerental.business.items.boundary.ItemsUnavailableException;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.rental.boundary.Bookings;
import com.github.noobymatze.bikerental.business.rental.entity.Billing;
import com.github.noobymatze.bikerental.business.rental.entity.Booking;
import com.github.noobymatze.bikerental.business.rental.entity.Offering;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Inject
    Items items;

    @Inject
    Bookings bookings;

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
     * Creates a valid booking with the given items.
     * 
     * @param customer
     * @param items
     * @param duration
     * @return 
     * @throws ItemsUnavailableException 
     * @throws TooManyBookingsForDurationException 
     */
    public Booking book(
        Customer customer,
        List<Item> items,
        Duration duration
    ) throws ItemsUnavailableException, TooManyBookingsForDurationException {
        return createBooking(customer, items, duration, null);
    }

    /**
     * Create a valid booking for the items with the given offering.
     * 
     * @param customer The customer wanting to book
     * @param items the items
     * @param duration for the specified duration
     * @param offering with an offering
     * @return 
     * @throws ItemsUnavailableException 
     * @throws TooManyBookingsForDurationException 
     */
    public Booking bookWithOffering(
        @NotNull Customer customer,
        @NotNull List<Item> items,
        @NotNull Duration duration, 
        @NotNull Offering offering
    ) throws ItemsUnavailableException, TooManyBookingsForDurationException {
        return createBooking(customer, items, duration, offering);
    }
    
    private Booking createBooking(
        Customer customer,
        List<Item> toBeRented,
        Duration duration, 
        Offering offering
    ) throws ItemsUnavailableException, TooManyBookingsForDurationException {
        List<Item> unavailableItems = toBeRented.stream().
            filter(item -> !items.isAvailableDuring(item, duration)).
            collect(toList());

        if (!unavailableItems.isEmpty()) {
            throw new ItemsUnavailableException(
                "There are unavailable items during the specified duration.",
                unavailableItems,
                duration
            );
        }

        if (bookings.existsDuring(customer, duration)) {
            throw new TooManyBookingsForDurationException(
                customer, 
                duration
            );
        }

        return bookings.save(
            Booking.builder().
            createdAt(ZonedDateTime.now()).
            duration(duration).
            details(RentalDetails.builder().
                customer(customer).
                offering(offering).
                build()).
            build()
        );
    }

    /**
     * 
     * @param customer
     * @param address 
     */
    public void addAddress(Customer customer, Address address) {
    }
    
}
