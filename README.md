# Bike rental

This application is just a simple implementation of a bike rental service and
is mainly used for exploring JPA at the FH Wedel.

It uses the capabilities of Java EE 7 to implement the application.

## Development setup

[Vagrant](https://www.vagrantup.com/) can be used to setup the database and the
[WildFly](http://wildfly.org/) application server. After setting up Vagrant, just
run:

```bash
$ vagrant up
```

This will install a MySQL database inside of the VM, expose it on the host on
port 3317, download and configure WildFly and move it inside the source repository.
Then it can be added to your favorite IDE.

Go make some tea, this will take a while...

## Running integration tests

For running the integration tests the vagrant VM needs to be running and
[Maven](http://maven.apache.org) has to be installed:

```bash
$ mvn failsafe:integration-test
```

