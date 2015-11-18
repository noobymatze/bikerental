#!/usr/bin/env bash

## Update and install dependencies
sudo apt-get update
sudo apt-get install -y software-properties-common python-software-properties
sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update

# Needed answers for automatic installation of mysql and java
sudo debconf-set-selections <<< 'mysql-server-5.6 mysql-server/root_password password sql'
sudo debconf-set-selections <<< 'mysql-server-5.6 mysql-server/root_password_again password sql'
sudo debconf-set-selections <<< 'oracle-java8-installer shared/accepted-oracle-license-v1-1 select true'

sudo apt-get install -y oracle-java8-installer mysql-server-5.6
sudo apt-get install -y oracle-java8-set-default

# Setup database
echo "DROP DATABASE IF EXISTS test" | mysql -uroot -psql
echo "CREATE USER 'wildfly'@'localhost' IDENTIFIED BY 'wildfly'" | mysql -uroot -psql
echo "CREATE DATABASE bikerental" | mysql -uroot -psql
echo "GRANT ALL ON bikerental.* TO 'wildfly'@'localhost'" | mysql -uroot -psql
echo "FLUSH PRIVILEGES" | mysql -uroot -psql

