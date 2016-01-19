package com.github.noobymatze.bikerental.business.addresses.boundary;

import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Provides methods for accessing address entities.
 *
 * @author Matthias Metzger
 */
@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class Addresses {

    @PersistenceContext(unitName = "bikerental")
    EntityManager em;

    /**
     * Persists or updates the given address.
     * 
     * @param address The NonNull address to be saved.
     * @return The saved address with an automatically generated primary
     * key, if it was persisted instead of updated.
     */
    public Address save(@NotNull Address address) {
        return em.merge(address);
    }

}
