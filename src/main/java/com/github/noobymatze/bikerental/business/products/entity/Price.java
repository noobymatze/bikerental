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

/**
 * Represents the price during a specified period of time.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "timed_price")
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Currency {
        EUR("€"),
        USD("$"),
        GBP(" ");

        private final String symbol;

        private Currency(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        @Override
        public String toString() {
            return getSymbol();
        }
    }

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format(
            "%s €",
            amount
        );
    }

}
