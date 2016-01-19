package com.github.noobymatze.bikerental.business.rental.boundary;

import com.github.noobymatze.bikerental.business.rental.entity.Billing;
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
