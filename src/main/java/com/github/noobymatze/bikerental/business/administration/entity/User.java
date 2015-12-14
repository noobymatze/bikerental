package com.github.noobymatze.bikerental.business.administration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Represents an administrator of the rental company. 
 * They are able to administer articles and users.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public class User extends Person {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return getEmail();
    }
    
}
