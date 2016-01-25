package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.items.entity.ItemModel;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Objects.nonNull;
import java.util.function.Function;
import java.util.stream.Collectors;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Aggregates the details concerning a specific 
 * rental of a cu
 * 
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "rental_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToMany
    private final List<Item> rentedItems = new ArrayList<>();

    @ManyToOne(cascade = {REFRESH})
    private Offer offer;

	@ManyToOne(optional = false)
	private Customer customer;

	public BigDecimal getPrice(Duration duration) {
        if (nonNull(offer)) {
            Map<ItemModel, Integer> dist = offer.getModels().stream().
                collect(Collectors.toMap(Function.identity(), e -> 1, (a, b) -> a + b));

            BigDecimal priceForItemsNotInOffer = BigDecimal.ZERO;
            for (Item item : rentedItems) {
                Integer counts = dist.getOrDefault(item.getModel(), 0);
                if (counts > 0) {
                    dist.put(item.getModel(), counts - 1);
                }
                else {
                    priceForItemsNotInOffer = priceForItemsNotInOffer.
                        add(item.getModel().getPriceForDuration(duration));
                }
            }

            return BigDecimal.valueOf(duration.getMinutes()).
                multiply(offer.getPricePerMinute()).
                add(priceForItemsNotInOffer);
        }
        else {
            return rentedItems.stream().
                map(Item::getModel).
                map(model -> model.getPriceForDuration(duration)).
                reduce(BigDecimal.ZERO, BigDecimal::add);
        }
	}

}
