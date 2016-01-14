package com.github.noobymatze.bikerental.business.items.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an item for this bike rental service. This can
 * be anything and should be extended accordingly.
 *
 * @author Matthias Metzger
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String description;

    @Column(name = "purchase_date")
    private ZonedDateTime purchaseDate;

	@ManyToOne(cascade = {PERSIST, MERGE, REFRESH})
	private Company manufacturer;
	
}
