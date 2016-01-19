package com.github.noobymatze.bikerental.business.items.entity;

import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Basically represents a category of an item. This is mainly
 * to be able to use this in an offer, since using the items
 * directly will cause problems down the line.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "item_model")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @OneToMany(mappedBy = "model")
    private final List<Item> items = new ArrayList<>();

    private BigDecimal pricePerMinute;

    public BigDecimal getPriceForDuration(Duration duration) {
        return BigDecimal.valueOf(duration.getMinutes()).multiply(pricePerMinute);
    }
	
}
