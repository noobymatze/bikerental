package com.github.noobymatze.bikerental.business.items.boundary;

import com.github.noobymatze.bikerental.business.items.entity.Bike;
import com.github.noobymatze.bikerental.business.items.entity.Broken;
import com.github.noobymatze.bikerental.business.items.entity.Broken_;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.items.entity.Repairment;
import com.github.noobymatze.bikerental.business.items.entity.Repairment_;
import com.github.noobymatze.bikerental.business.rental.entity.Booking;
import com.github.noobymatze.bikerental.business.rental.entity.Booking_;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails;
import com.github.noobymatze.bikerental.business.rental.entity.RentalDetails_;
import com.github.noobymatze.bikerental.business.rental.entity.Trip;
import com.github.noobymatze.bikerental.business.rental.entity.Trip_;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Matthias Metzger
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
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
        return !isBooked(item, duration) 
            && !isBroken(item, duration)
            && !isInReparation(item, duration)
            && !isOnTour(item, duration);
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
        CriteriaQuery<Broken> q = cb.createQuery(Broken.class);
        Root<Broken> broken = q.from(Broken.class);

        q.where(
            cb.equal(broken.get(Broken_.item), item)
        );

        List<Broken> result = em.createQuery(q).getResultList();
        if (result.isEmpty()) {
            return false;
        }

        CriteriaQuery<Repairment> rq = cb.createQuery(Repairment.class);
        Root<Repairment> reparation = rq.from(Repairment.class);

        rq.where(
            reparation.get(Repairment_.items).in(result)
        );

        return em.createQuery(rq).getResultList().stream().
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

        List<Booking> result = em.createQuery(q).getResultList().stream().
            filter(b -> b.getDetails().getRentedItems().contains(item)).
            filter(b -> b.getDuration().containsPart(duration)).
            peek(System.out::println).
            collect(Collectors.toList());

        return !result.isEmpty();

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
        CriteriaQuery<Trip> q = cb.createQuery(Trip.class);
        Root<Trip> tour = q.from(Trip.class);
        Join<Trip, RentalDetails> details = tour.join(Trip_.details);

        q.where(
            cb.isMember(item, details.get(RentalDetails_.rentedItems))
        );

        return em.createQuery(q).getResultList().stream().
            anyMatch(t -> t.getDuration().containsPart(duration));
    }
    
    /**
     * Returns all available Items during the specified time.
     * 
     * @param duration
     * @return 
     */
    public List<Item> getAvailableItemsDuring(Duration duration) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> q = cb.createQuery(Item.class);
        q.from(Item.class);

        // Highly inefficient, due to In-Memory processing - but the easiest way.
        return em.createQuery(q).getResultList().stream().
            filter(item -> isAvailableDuring(item, duration)).
            collect(toList());
    }

    /**
     * 
     * @param item
     * @return 
     */
    public Item save(@NotNull Item item) {
        return em.merge(item);
    }
    
}
