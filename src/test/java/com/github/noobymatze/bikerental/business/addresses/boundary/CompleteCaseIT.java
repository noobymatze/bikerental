package com.github.noobymatze.bikerental.business.addresses.boundary;

import com.github.noobymatze.bikerental.business.BaseTest;
import com.airhacks.rulz.em.EntityManagerProvider;
import static com.airhacks.rulz.em.EntityManagerProvider.persistenceUnit;
import com.github.noobymatze.bikerental.business.addresses.entity.Address;
import com.github.noobymatze.bikerental.business.addresses.entity.City;
import com.github.noobymatze.bikerental.business.addresses.entity.Country;
import com.github.noobymatze.bikerental.business.addresses.entity.Street;
import com.github.noobymatze.bikerental.business.addresses.entity.Zipcode;
import com.github.noobymatze.bikerental.business.administration.boundary.Authenticator;
import com.github.noobymatze.bikerental.business.administration.boundary.Customers;
import com.github.noobymatze.bikerental.business.administration.boundary.Employees;
import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.administration.entity.Employee;
import com.github.noobymatze.bikerental.business.items.boundary.Items;
import com.github.noobymatze.bikerental.business.items.entity.Bike;
import com.github.noobymatze.bikerental.business.items.entity.Company;
import com.github.noobymatze.bikerental.business.items.entity.Helmet;
import com.github.noobymatze.bikerental.business.items.entity.ItemModel;
import com.github.noobymatze.bikerental.business.rental.boundary.Billings;
import com.github.noobymatze.bikerental.business.rental.boundary.Bookings;
import com.github.noobymatze.bikerental.business.rental.boundary.Trips;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Matthias Metzger
 */
public class CompleteCaseIT {

    // Bootstrapping and stuff
    @Rule
    public final EntityManagerProvider emp = persistenceUnit("it");

    private final Authenticator authenticator = new Authenticator(emp.em());
    private final Bookings bookings = new Bookings(emp.em());
    private final Items items = new Items(emp.em());
    private final Billings billings = new Billings(emp.em());
    private final Trips trips = new Trips(emp.em());
    private final Addresses addresses = new Addresses(emp.em());
    private final Customers customers = new Customers(emp.em(), items, bookings);
    private final Employees employees = new Employees(emp.em(), trips, billings);

    protected final Company fh = Company.builder().
        name("FH Wedel").
        build();

    protected final ItemModel unicornBike = ItemModel.builder().
        model("Unicorn").
        pricePerMinute(BigDecimal.valueOf(0.08)).
        build();

    protected final ItemModel optimusHelmet = ItemModel.builder().
        model("Optimus Prime").
        pricePerMinute(BigDecimal.valueOf(0.02)).
        build();

    protected final Duration fifteenthOfJanuar;

    protected final List<Bike> randomBikes = asList(
        createBike(null, unicornBike, fh),
        createBike(null, unicornBike, fh),
        createBike(null, unicornBike, fh)
    );

    protected final Customer matthias = Customer.builder().
        createdAt(ZonedDateTime.now()).
        email("inf101522@fh-welde.de").
        password("secret").build();

    protected final Employee bernddasbrot;

    public CompleteCaseIT() {
        this.bernddasbrot = new Employee();
        this.bernddasbrot.setCreatedAt(ZonedDateTime.now());
        this.bernddasbrot.setEmail("bernd@dasbrot.de");
        this.bernddasbrot.setPassword("höhö");

        this.matthias.setFirstName("Matthias");
        this.matthias.setLastName("Metzger");

        this.fifteenthOfJanuar = new Duration();
        this.fifteenthOfJanuar.setStart(ZonedDateTime.of(2016, 1, 15, 8, 0, 0, 0, ZoneId.systemDefault()));
        this.fifteenthOfJanuar.setEnd(ZonedDateTime.of(2016, 1, 15, 16, 0, 0, 0, ZoneId.systemDefault()));
    }

    @Before
    public void init() {
        EntityTransaction tx = emp.em().getTransaction();
        tx.begin();
        Country germany = Country.withName("Germany");
        City berlin = City.withNameIn("Berlin", germany);
        Zipcode berlinZip = Zipcode.withCity("10557", berlin);
        Street willyBrandtStreet = Street.withNameAndZip("Willy-Brandt-Straße", berlinZip);
        Address somewhereInGermany = Address.withHouseAndZip(1, willyBrandtStreet);
        Address saved = addresses.save(somewhereInGermany);

        randomBikes.forEach(items::save);
        customers.save(matthias);
        employees.save(bernddasbrot);
        tx.commit();
    }

    private Bike createBike(Long id, ItemModel model, Company company) {
        Bike bike = new Bike();
        bike.setId(id);
        bike.setDescription("Some cool bike");
        bike.setManufacturer(company);
        bike.setModel(model);
        bike.setPurchaseDate(ZonedDateTime.now());
        model.getItems().add(bike);
        return bike;
    }

    private Helmet createHelmet(Long id, ItemModel model, Company company) {
        Helmet helmet = new Helmet();
        helmet.setId(id);
        helmet.setDescription("Some cool bike");
        helmet.setManufacturer(company);
        helmet.setModel(model);
        helmet.setPurchaseDate(ZonedDateTime.now());
        model.getItems().add(helmet);
        return helmet;
    }

    @Test
    public void testEverything() {
        EntityTransaction tx = emp.em().getTransaction();
        tx.begin();
        Optional<Customer> value = authenticator.tryAuthenticate(matthias.getEmail(), matthias.getPassword());
        Assert.assertTrue(value.isPresent());
        tx.commit();
    }

}
