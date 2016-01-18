package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.time.entity.Duration;

/**
 *
 * @author Matthias Metzger
 */
class TooManyBookingsForDurationException extends Exception {

    private final Customer customer;
    private final Duration duration;

    public TooManyBookingsForDurationException(Customer customer, Duration duration) {
        this("A customer can only create one booking for a specified duration.", customer, duration);
    }

    public TooManyBookingsForDurationException(String message, Customer customer, Duration duration) {
        super(message);

        this.customer = customer;
        this.duration = duration;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Duration getDuration() {
        return duration;
    }
    
}
