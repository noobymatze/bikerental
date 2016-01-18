package com.github.noobymatze.bikerental.business.items.boundary;

import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.util.List;

/**
 * Should be used, when the items are not available for a given
 * duration.
 *
 * @author Matthias Metzger
 */
public class ItemsUnavailableException extends Exception {

    private final List<Item> items;
    private final Duration duration;

    public ItemsUnavailableException(String message, List<Item> items, Duration duration) {
        super(message);

        this.items = items;
        this.duration = duration;
    }

    public List<Item> getItems() {
        return items;
    }

    public Duration getDuration() {
        return duration;
    }
    
}
