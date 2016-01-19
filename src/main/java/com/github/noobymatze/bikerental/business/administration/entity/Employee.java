package com.github.noobymatze.bikerental.business.administration.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Represents an employee of the rental company.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "employee")
public class Employee extends Customer {

}
