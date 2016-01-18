package com.github.noobymatze.bikerental.business.items.entity;

import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.io.Serializable;
import java.time.ZonedDateTime;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an item in a broken state. It is possible to
 * 
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "broken")
@Getter
@Setter
public class Broken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Duration duration;

    private ZonedDateTime scheduledRepair;

    private boolean unrepairable = false;

    @ManyToOne(cascade = REFRESH)
    private Item item;

}
