package com.github.noobymatze.bikerental.business.items.entity;

import com.github.noobymatze.bikerental.business.rental.entity.Discount;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "rentable_item")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentableItem extends Item {

	private BigDecimal pricePerMinute;

	private final List<Discount> discounts = new ArrayList<>();

	/**
	 * Returns the total price for this item via from and to regulations.
	 * 
     * @param tp
	 * @return 
	 */
	public BigDecimal getPriceForTime(Duration tp) {
		return BigDecimal.valueOf(tp.getMinutes()).multiply(pricePerMinute);
	}
	
}
