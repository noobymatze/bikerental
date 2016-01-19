package com.github.noobymatze.bikerental.business.items.entity;

import com.github.noobymatze.bikerental.business.time.entity.Duration;
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an item for this bike rental service. This can
 * be anything and should be extended accordingly.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

    private String description;

    @Column(name = "purchase_date")
    private ZonedDateTime purchaseDate;

    private boolean deleted = false;

	@ManyToOne(optional = false, cascade = {PERSIST, MERGE, REFRESH})
	private Company manufacturer;

    @ManyToOne(optional = false, cascade = {PERSIST, MERGE, REFRESH})
    private ItemModel model;

}
