package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a discount for the specified product during the given time
 * period. 
 * 
 * <ul>
 * <li>Only one discount per specified time period per product is allowed.</li>
 * <li>The percentage should not be greater than 50%.</li>
 * </ul>
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "offering")
@Getter
@Setter
public class Offering implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal pricePerMinute;

    @Embedded
    private Duration duration;

    @ManyToMany
    private final List<Item> items = new ArrayList<>();

}
