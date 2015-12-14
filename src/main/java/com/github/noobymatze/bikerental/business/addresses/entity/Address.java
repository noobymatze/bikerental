package com.github.noobymatze.bikerental.business.addresses.entity;

import java.io.Serializable;
import static java.util.Objects.isNull;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents an address in the real world.
 *
 * @author Matthias Metzger
 */
@Entity
@Table(name = "address")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "house_number")
    private Integer houseNumber;

    private String addition;

    @ManyToOne(optional = false, cascade = {MERGE, PERSIST, REFRESH})
    private Street street;

    @Override
    public String toString() {
        String text = houseNumber.toString();
        if (isNull(addition)) {
            text = houseNumber + " " + addition;
        }

        return String.format("%s, %s", text, street);
    }
    
    /**
     * Creates a new address with the given house number in
     * the specified street.
     * 
     * @param house The number of the house.
     * @param street The street in which this house can be found.
     * @return Fresh address.
     */
    public static Address withHouseAndZip(Integer house, Street street) {
        return new AddressBuilder().
            houseNumber(house).
            street(street).
            build();
    }

}
