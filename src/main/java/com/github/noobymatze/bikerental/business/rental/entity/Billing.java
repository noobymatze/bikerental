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
	private Tour tour;

	public BigDecimal getPrice() {
		return tour.getPrice();
	}
	
	
}
