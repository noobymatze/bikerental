package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.nonNull;
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
    private Offering offering;

	@ManyToOne(optional = false)
	private Customer customer;

	public BigDecimal getPrice(Duration tp) {
        if (nonNull(offering)) {
            return BigDecimal.
                valueOf(tp.getMinutes()).
                multiply(offering.getPricePerMinute());
        }
        else {
            return rentedItems.stream().
                map(item -> item.getPriceForDuration(tp)).
                reduce(BigDecimal.ZERO, BigDecimal::add);
        }
	}

}
