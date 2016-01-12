package com.github.noobymatze.bikerental.business.rental.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents the reservation for a product for a specific period of
 * time.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "reservation")
@Getter
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    private boolean cancelled;

	@Column(name = "start_time")
	private ZonedDateTime start;

	@Column(name = "end_time")
	private ZonedDateTime end;

	@OneToOne(optional = false, cascade = {PERSIST, MERGE, REFRESH})
	private RentalDetails details;

	public BigDecimal getEstimatedPrice() {
		return details.getPrice(start, end);
	}

}
