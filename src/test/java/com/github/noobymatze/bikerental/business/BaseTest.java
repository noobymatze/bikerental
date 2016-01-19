package com.github.noobymatze.bikerental.business;

import com.github.noobymatze.bikerental.business.administration.entity.Customer;
import com.github.noobymatze.bikerental.business.administration.entity.Employee;
import com.github.noobymatze.bikerental.business.items.entity.Bike;
import com.github.noobymatze.bikerental.business.items.entity.Company;
import com.github.noobymatze.bikerental.business.items.entity.Helmet;
import com.github.noobymatze.bikerental.business.items.entity.Item;
import com.github.noobymatze.bikerental.business.items.entity.ItemModel;
import com.github.noobymatze.bikerental.business.time.entity.Duration;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static java.util.Arrays.asList;
import java.util.List;

/**
 * Containes dummy data for most of the objects needed.
 *
 * @author Matthias Metzger
 */
public abstract class BaseTest {

    protected final Company fh = Company.builder().
        id(1L).
        name("FH Wedel").
        build();

    protected final ItemModel unicornBike = ItemModel.builder().
        id(1L).
        model("Unicorn").
        pricePerMinute(BigDecimal.valueOf(0.08)).
        build();

    protected final ItemModel optimusHelmet = ItemModel.builder().
        id(2L).
        model("Optimus Prime").
        pricePerMinute(BigDecimal.valueOf(0.02)).
        build();

    protected final Duration fifteenthOfJanuar;

    protected final List<Bike> randomBikes = asList(
        createBike(1L, unicornBike, fh),
        createBike(2L, unicornBike, fh),
        createBike(3L, unicornBike, fh)
    );

    protected final Customer matthias = Customer.builder().
        createdAt(ZonedDateTime.now()).
        email("inf101522@fh-welde.de").
        password("secret").build();

    protected final Employee bernddasbrot;

    public BaseTest() {
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

}
