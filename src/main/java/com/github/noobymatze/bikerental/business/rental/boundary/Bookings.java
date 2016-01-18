package com.github.noobymatze.bikerental.business.rental.boundary;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.rental.entity.Booking;
import com.github.noobymatze.bikerental.business.rental.entity.Booking_;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails_;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matthias Metzger
 */
@Stateless
public class Bookings {

    @PersistenceContext
    EntityManager em;
    
    /**
     * Tests, whether the given customer already booked something
     * during the specified duration.
     * 
     * @param customer The customer to be tested
     * @param duration during the duration
     * @return True if 
     */
    public boolean existsDuring(Customer customer, Duration duration) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Booking> q = cb.createQuery(Booking.class);
        Root<Booking> booking = q.from(Booking.class);
        Join<Booking, RentalDetails> details = booking.join(Booking_.details);

        q.where(
            cb.equal(details.get(RentalDetails_.customer), customer),
            cb.isFalse(booking.get(Booking_.canceled))
        );

        return em.createQuery(q).getResultList().stream().
            anyMatch(booked -> booked.getDuration().containsPart(duration));
    }

    /**
     * Save a given Booking.
     * 
     * @param booking
     * @return 
     */
    public Booking save(@NotNull Booking booking) {
        return em.merge(booking);
    }

    /**
     * Cancels the given booking.
     * 
     * @param booking Booking to be canceled.
     * @return New saved and canceled booking.
     */
    public Booking cancel(@NotNull Booking booking) {
        booking.setCanceled(true);
        return em.merge(booking);
    }
    
}
