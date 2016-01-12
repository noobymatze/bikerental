package com.github.noobymatze.bikerental.business.products.entity;

import com.github.noobymatze.bikerental.business.rental.entity.Discount;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "rentable_item")
public class RentableItem extends Item {

	private BigDecimal pricePerMinute;

	private final List<Discount> discounts = new ArrayList<>();

	/**
	 * Returns the total price for this item via from and to regulations.
	 * 
	 * @param from
	 * @param to
	 * @return 
	 */
	public BigDecimal getPriceForTime(ZonedDateTime from, ZonedDateTime to) {
		long minutes = from.until(to, ChronoUnit.MINUTES);
		return BigDecimal.valueOf(minutes).multiply(pricePerMinute);
	}
	
}
