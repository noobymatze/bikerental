package com.github.noobymatze.bikerental.business.rental.boundary;

import com.github.noobymatze.bikerental.business.rental.entity.Trip;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matthias Metzger
 */
@Stateless
public class Trips {

    @PersistenceContext
    EntityManager em;

    /**
     * Save the given trip.
     * 
     * @param trip
     * @return 
     */
    public Trip save(@NotNull Trip trip) {
        return em.merge(trip);
    }
    
}
