package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.administration.entity.Employee;
import com.github.noobymatze.bikerental.business.items.entity.Broken;
import com.github.noobymatze.bikerental.business.items.entity.Company;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.items.entity.ItemModel;
import com.github.noobymatze.bikerental.business.items.entity.Repairment;
import com.github.noobymatze.bikerental.business.rental.boundary.Billings;
import com.github.noobymatze.bikerental.business.rental.boundary.Trips;
import com.github.noobymatze.bikerental.business.rental.entity.Billing;
import com.github.noobymatze.bikerental.business.rental.entity.Booking;
import com.github.noobymatze.bikerental.business.rental.entity.Offer;
import com.github.noobymatze.bikerental.business.rental.entity.Trip;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import static java.util.Objects.nonNull;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Matthias Metzger
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @PersistenceContext
    EntityManager em;

    @Inject
    Trips trips;

    @Inject
    Billings billings;

    /**
     * Releases the booking to the given customer and let's them
     * go their way.
     * 
     * @param employee
     * @param booking
     * @return 
     */
    public Trip sendCustomersOnTheirWay(Employee employee, Booking booking, ZonedDateTime time) {
        Duration duration = new Duration();
        duration.setStart(time);

        return trips.save(Trip.builder().
            duration(duration).
            details(booking.getDetails()).
            build()
        );
    }
    
    /**
     * Welcome the customers back and create a billing. Also save the
     * broken stuff.
     * 
     * @param trip
     * @param time
     * @param brokenStuff Everything that broke during this tour and
     * is therefore rendered unusable for some time.
     * @return 
     */
    public Billing welcomeBack(Trip trip, ZonedDateTime time, Item... brokenStuff) {
        trip.getDuration().setEnd(time);

        if (nonNull(brokenStuff)) {
            Stream.of((Item[]) brokenStuff).
                map(item -> Broken.fromItem(item, time)).
                forEach(em::merge);
        }

        return billings.save(
            Billing.fromTrip(trip)
        );
    }

    public Offer makeOfferDuring(String name, Duration duration, List<ItemModel> items, BigDecimal combinedPrice) {
        Offer offer = new Offer();
        offer.setName(name);
        offer.setDuration(duration);
        offer.setPricePerMinute(combinedPrice);
        offer.getModels().addAll(items);
        return em.merge(offer);
    }

    /**
     * Schedule a new reparation for the given Duration with the given Company.
     * 
     * @param company
     * @param duration How long will it take?
     * @param brokenItems Which items will be fixed?
     * @return Reparation.
     */
    public Repairment scheduleRepairment(Company company, Duration duration, List<Broken> brokenItems) {
        Repairment r = new Repairment();
        brokenItems.stream().
            peek(b -> b.setScheduledRepair(duration.getStart())).
            forEach(r::addItem);

        r.setDuration(duration);
        r.setCompany(company);
        return em.merge(r);
    }

    public Employee save(Employee bernddasbrot) {
        return em.merge(bernddasbrot);
    }
    
}
