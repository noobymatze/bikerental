package com.github.noobymatze.bikerental.business.administration.boundary;

import com.github.noobymatze.bikerental.business.BaseTest;
import com.github.noobymatze.bikerental.business.items.boundary.Items;
import com.github.noobymatze.bikerental.business.items.boundary.ItemsUnavailableException;
import com.github.noobymatze.bikerental.business.rental.boundary.Bookings;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

/**
 * Just some simple unit tests for making sure, that exceptions
 * are thrown in specific cases.
 *
 * @author Matthias Metzger
 */
public class CustomersTest extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(CustomersTest.class.getName());

    private Customers customers;
    
    @Before
    public void setUp() {
        this.customers = new Customers();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBookEmptyItems() throws ItemsUnavailableException, TooManyBookingsForDurationException {
        customers.book(matthias, new ArrayList<>(), null);
    }

    @Test(expected = ItemsUnavailableException.class)
    public void testItemsUnavailable() throws ItemsUnavailableException, TooManyBookingsForDurationException {
        Items items = mock(Items.class);
        when(items.isAvailableDuring(null, fifteenthOfJanuar)).thenReturn(Boolean.FALSE);
        this.customers.items = items;
        this.customers.book(matthias, randomBikes, fifteenthOfJanuar);
    }

    @Test(expected = ItemsUnavailableException.class)
    public void testTooManyBookings() throws ItemsUnavailableException, TooManyBookingsForDurationException {
        Items items = mock(Items.class);
        when(items.isAvailableDuring(null, fifteenthOfJanuar)).thenReturn(Boolean.TRUE);
        this.customers.items = items;

        Bookings bookings = mock(Bookings.class);
        this.customers.bookings = bookings;
        when(bookings.existsDuring(matthias, fifteenthOfJanuar)).thenReturn(Boolean.FALSE);
        this.customers.book(matthias, randomBikes, fifteenthOfJanuar);
    }

}
