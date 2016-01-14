package com.github.noobymatze.bikerental.business.items.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Represents a tire pump for different Bikes.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "tirepump")
@Getter
public class TirePump extends RentableItem {

}
