package com.github.noobymatze.bikerental.business.items.boundary;

import com.github.noobymatze.bikerental.business.items.entity.Broken;
import com.github.noobymatze.bikerental.business.items.entity.Broken_;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.items.entity.Reparation;
import com.github.noobymatze.bikerental.business.items.entity.Reparation_;
import com.github.noobymatze.bikerental.business.rental.entity.Booking;
import com.github.noobymatze.bikerental.business.rental.entity.Booking_;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails_;
import com.github.noobymatze.bikerental.business.rental.entity.Tour;
import com.github.noobymatze.bikerental.business.rental.entity.Tour_;
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
public class Items {

    @PersistenceContext
    EntityManager em;

    /**
     * Tests whether the given Item is available.
     * 
     * @param item 
     * @param duration 
     * @return  
     */
    public boolean isAvailableDuring(@NotNull Item item, Duration duration) {
        return isBooked(item, duration) 
            || isBroken(item, duration)
            || isInReparation(item, duration)
            || isOnTour(item, duration);
    }

    /**
     * Tests whether the given item is broken during the specified
     * duration.
     * 
     * @param item The item to be tested
     * @param duration The duration in which the item could broken.
     * @return True if the item is broken, false otherwise.
     */
    public boolean isBroken(@NotNull Item item, Duration duration) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Broken> q = cb.createQuery(Broken.class);
        Root<Broken> broken = q.from(Broken.class);

        q.where(
            cb.equal(broken.get(Broken_.item), item)
        );

        return em.createQuery(q).getResultList().stream().
            anyMatch(b -> b.getDuration().containsPart(duration));
    }

    /**
     * Tests whether the given item is in reparation during the specified
     * duration.
     * 
     * @param item The item to be tested
     * @param duration The duration in which the item could be on tour.
     * @return True if the item is in reparation, false otherwise.
     */
    public boolean isInReparation(@NotNull Item item, Duration duration) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reparation> q = cb.createQuery(Reparation.class);
        Root<Reparation> reparation = q.from(Reparation.class);


        q.where(
            cb.equal(reparation.get(Reparation_.items), item)
        );

        return em.createQuery(q).getResultList().stream().
            anyMatch(b -> b.getDuration().containsPart(duration));
    }

    /**
     * Tests whether the given item is already booked during the specified
     * duration.
     * 
     * @param item The item to be tested
     * @param duration The duration in which the item could already booked.
     * @return True if the item is already booked, false otherwise.
     */
    public boolean isBooked(Item item, Duration duration) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Booking> q = cb.createQuery(Booking.class);
        Root<Booking> booking = q.from(Booking.class);
        Join<Booking, RentalDetails> details = booking.join(Booking_.details);

        q.where(
            cb.equal(details.get(RentalDetails_.rentedItems), item)
        );

        return em.createQuery(q).getResultList().stream().
            anyMatch(b -> b.getDuration().containsPart(duration));
    }

    /**
     * Tests whether the given item is on a tour during the specified
     * duration.
     * 
     * @param item The item to be tested
     * @param duration The duration in which the item could be on tour.
     * @return True if the item is on tour, false otherwise.
     */
    public boolean isOnTour(@NotNull Item item, Duration duration) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tour> q = cb.createQuery(Tour.class);
        Root<Tour> tour = q.from(Tour.class);
        Join<Tour, RentalDetails> details = tour.join(Tour_.details);

        q.where(
            cb.equal(details.get(RentalDetails_.rentedItems), item)
        );

        return em.createQuery(q).getResultList().stream().
            anyMatch(t -> t.getDuration().containsPart(duration));
    }
    
}
