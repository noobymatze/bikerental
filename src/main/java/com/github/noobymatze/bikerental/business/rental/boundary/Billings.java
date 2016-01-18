package com.github.noobymatze.bikerental.business.rental.boundary;

import com.github.noobymatze.bikerental.business.rental.entity.Billing;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Matthias Metzger
 */
@Stateless
public class Billings {
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     * @param billing
     * @return 
     */
    public Billing save(@NotNull Billing billing) {
        return em.merge(billing);
    }
    
}
