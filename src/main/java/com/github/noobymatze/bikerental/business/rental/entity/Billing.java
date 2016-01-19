package com.github.noobymatze.bikerental.business.rental.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

/**
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "billing")
@Getter
public class Billing implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional = false)
	private Trip trip;

    private boolean payed;

	public BigDecimal getPrice() {
		return trip.getPrice();
	}

    /**
     * Create a new Billing from the given trip.
     * 
     * @param trip
     * @return 
     */
    public static Billing fromTrip(Trip trip) {
        Billing billing = new Billing();
        billing.trip = trip;
        return billing;
    }
	
}
