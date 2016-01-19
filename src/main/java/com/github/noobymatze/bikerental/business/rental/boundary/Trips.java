package com.github.noobymatze.bikerental.business.rental.boundary;

import com.github.noobymatze.bikerental.business.rental.entity.Trip;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
