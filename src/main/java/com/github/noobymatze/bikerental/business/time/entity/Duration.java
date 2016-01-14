package com.github.noobymatze.bikerental.business.time.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

/**
 *
 * @author Matthias Metzger
 */
@Embeddable
@Getter
public class Duration implements Serializable {

    @Column(name = "start_time")
    private ZonedDateTime start;

    @Column(name = "end_time")
    private ZonedDateTime end;

    /**
     * Sets the start time and throws an exception if it is greater
     * than the ending value.
     * 
     * @param start 
     */
    public void setStart(ZonedDateTime start) {
        if (nonNull(end) && start.compareTo(end) > 0) {
            throw new IllegalArgumentException("The starting time cannot be greater than the end time.");
        }

        this.start = start;
    }

    /**
     * Set the end time and throw an exception if it is smaller than
     * the starting value.
     * 
     * @param end 
     */
    public void setEnd(ZonedDateTime end) {
        if (nonNull(start) && end.compareTo(start) < 0) {
            throw new IllegalArgumentException("The ending time cannot be smaller than the starting time.");
        }

        this.end = end;
    }

    /**
     * Calculate the minutes per
     * 
     * @return 
     */
    public long getMinutes() {
        if (isNull(start) || isNull(end)) {
            throw new IllegalStateException("Cannot calculate the minute difference, when one of em is empty.");
        }

        return start.until(end, ChronoUnit.MINUTES);
    }
    
}
