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
import lombok.Getter;

/**
 * Represents a time period.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "time_period")
@Getter
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

    @Override
    public String toString() {
        return String.format(
            "from %s to %s",
            FORMATTER.format(from), FORMATTER.format(to)
        );
    }

}
