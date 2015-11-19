package com.github.noobymatze.bikerental.business.administration.entity;

import com.github.noobymatze.bikerental.business.Person;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents an employee of the rental company.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "employee")
public class Employee extends Person {

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;

    public User getUser() {
        return user;
    }

}
