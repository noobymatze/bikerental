# Bike rental

This application is just a simple implementation of a bike rental service and
is mainly used for exploring JPA at the FH Wedel.

It uses the capabilities of Java EE 7 to implement the application.

## UML diagram

This diagram gives an overview of the underlying data model for the bike
rental service.

![UML diagram for a bikerental service](https://raw.githubusercontent.com/noobymatze/bikerental/master/bikerental-uml.png)

### Persons

The left hand side shows all persons, which can interact with the system.
The `Person` itself is an abstract base class for the customer and gathers
the data making a Person. The `Customer` extends a `Person` and is mainly 
concerned with the identification via email and password (since this is just
a students project, I won't be using password hashing).

Since an `Employee` may as well be a `Customer` of the system, it extends
the `Customer`. As of now, there is no additional data to be found, but
one could easily think of some properties of an `Employee`, like a role
for example.

Every `Customer` can have many addresses, which could live on their own,
when the `Customer` moves or dies. This might mean an additional overhead,
when thinking about using addresses but, it also captures the real world
more accurately.

### Items and states

Any `Item` in the diagram was manufactured by a company and serves as a base
class for additional items. The implementation uses the following strategy
for the inheritance: `@Inheritance(strategy=JOINED)`.
This means the corresponding database schema will contain a table for `Item`
and for each subclass. This decision was based on the possibly large range
of properties for different items. Instead of throwing every property in
one table, this guarantees a simple and flexible way to extend the system
later on.

To be able to create offers, every item must belong to an `ItemModel`. Without
it, a given offer could only be valid for some actual items, which would
mean adding all of them explicitely to the `Offer`. Furthermore one would
not be able to distinguish, whether the given `Item` actually needs to be
booked or is only one of a category of items, which could be booked to 
get a discount. That's the problem, the `ItemModel` solves.

An item can be `Broken` and a `Broken` item can be scheduled for or 
currently be in, a `Repairment`. This capability can be used to track
the durability of an item over time and to understand when to buy new
Items.

This can become a problem, since to test whether a given item is free for
a specific period of time, all these Objects need to be taken into account.

### Rental

The actual rental of a bike has a defined lifecycle. At first it can be
booked for a period of time. The `Booking` can of course be canceled. 
Additionally the details of the `Booking` can contain an `Offer` for a
number of `ItemModel`s. 

When the time has come to go on a `Trip` or `Tour`, the `Customer` will be greeted
and given the rented items by an `Employee`, thus resulting in a state of `Tour`
in the UML diagram.

When the `Customer` returns, he will be welcomed back by an `Employee`, who
looks for `Broken` items and creates a billing for the corresponding `Tour`.
Since the details are all the same, they can be found separately in the `RentalDetails`
table. 

## Use cases

The following image shows the use cases of this application for documentation 
purposes only.

![Use cases for bikerental](https://raw.githubusercontent.com/noobymatze/bikerental/master/bikerental-use-cases-final.png)

## ER diagram

Shamelessly used MySQL Workbench to reverse engineer the schema.

![Entity relationship diagram](https://raw.githubusercontent.com/noobymatze/bikerental/master/bikerental-er.png)


