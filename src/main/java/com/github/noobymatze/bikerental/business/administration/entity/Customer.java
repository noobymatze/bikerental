package com.github.noobymatze.bikerental.business.administration.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a customer of the bike rental.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "customer")
public class Customer extends User {

}
