package com.github.noobymatze.bikerental.business.products.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents the price during a specified period of time.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "timed_price")
@Getter
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString(of = "symbol")
    public enum Currency {
        EUR("€"),
        USD("$"),
        GBP(" ");

        private final @Getter String symbol;

        private Currency(String symbol) {
            this.symbol = symbol;
        }

    }

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal amount;

    @Override
    public String toString() {
        return String.format(
            "%s €",
            amount
        );
    }

}
