package com.github.noobymatze.bikerental.business.rental.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a time period.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "time_period")
public class TimePeriod implements Serializable {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().
            appendPattern("MM/dd/yyyy HH:mm a").
            toFormatter();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @Column(name = "from_time")
    private ZonedDateTime from;

    @NotNull
    @Column(name = "to_time")
    private ZonedDateTime to;

    public ZonedDateTime getFrom() {
        return from;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getTo() {
        return to;
    }

    public Optional<String> getNameO() {
        return Optional.ofNullable(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
            "from %s to %s",
            FORMATTER.format(from), FORMATTER.format(to)
        );
    }

}
