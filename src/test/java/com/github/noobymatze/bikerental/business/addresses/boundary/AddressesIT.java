package com.github.noobymatze.bikerental.business.addresses.boundary;

import com.airhacks.rulz.em.EntityManagerProvider;
import static com.airhacks.rulz.em.EntityManagerProvider.persistenceUnit;
import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import com.github.noobymatze.bikerental.business.addresses.entity.City;
import com.github.noobymatze.bikerental.business.addresses.entity.Country;
import com.github.noobymatze.bikerental.business.addresses.entity.Street;
import com.github.noobymatze.bikerental.business.addresses.entity.Zipcode;
import javax.persistence.EntityTransaction;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Matthias Metzger
 */
public class AddressesIT {

    @Rule
    public final EntityManagerProvider emp = persistenceUnit("it");

    private Addresses addresses;

    @Before
    public void init() {
        this.addresses = new Addresses();
        this.addresses.em = emp.em();
    }
    
    @Test
    public void testSave() {
        EntityTransaction tx = emp.tx();
        tx.begin();
        Country germany = Country.withName("Germany");
        City berlin = City.withNameIn("Berlin", germany);
        Zipcode berlinZip = Zipcode.withCity("10557", berlin);
        Street willyBrandtStreet = Street.withNameAndZip("Willy-Brandt-Straße", berlinZip);
        Address somewhereInGermany = Address.withHouseAndZip(1, willyBrandtStreet);
        Address saved = addresses.save(somewhereInGermany);
        assertThat(somewhereInGermany.getHouseNumber(), is(saved.getHouseNumber()));
        tx.commit();
    }

}
