package com.github.noobymatze.bikerental.business.rental.entity;

import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.math.BigDecimal;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "trip")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Embedded
    private Duration duration;

	@OneToOne(optional = false, cascade = {PERSIST, MERGE, REFRESH})
	private RentalDetails details;

	public BigDecimal getPrice() {
		return details.getPrice(duration);
	}

}
