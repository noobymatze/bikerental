# Bikerental

This application is just a simple implementation of a bike rental service and
is mainly used for exploring JPA at the FH Wedel.

It uses the capabilities of Java EE 7 to implement the application.

## Running integration tests

For running the integration tests you need to setup an additional MySQL database
with a user named 'tester', who has the password 'testing'.

With that done and [Maven](1) installed on the system, the following command
will run the integration tests.

```bash
$ mvn failsafe:integration-test
```

[1]: https://maven.apache.org
