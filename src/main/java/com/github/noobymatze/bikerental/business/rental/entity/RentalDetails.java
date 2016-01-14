package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.administration.entity.User;
import com.github.noobymatze.bikerental.business.items.entity.RentableItem;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "rental_details")
@Getter
@Setter
public class RentalDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToMany(mappedBy = "reservation")
    @JoinTable(name = "details_has_items")
    private final List<RentableItem> rentedItems = new ArrayList<>();

	@ManyToOne
	private User user;

	public BigDecimal getPrice(Duration tp) {
		return rentedItems.stream().
			map(item -> item.getPriceForTime(tp)).
			reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
