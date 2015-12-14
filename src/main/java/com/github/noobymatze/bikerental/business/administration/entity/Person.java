package com.github.noobymatze.bikerental.business.administration.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * Represents a real world person with a name and some contact information.
 *
 * @author Matthias Metzger
 */
@MappedSuperclass
@Getter
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public String getFullName() {
        return String.format(
            "%s %s", 
            getFirstName(), getLastName()
        );
    }

    @Override
    public String toString() {
        return getFullName();
    }
    
}
