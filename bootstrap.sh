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
echo "CREATE USER 'wildfly'@'%' IDENTIFIED BY 'wildfly'" | mysql -uroot -psql
echo "CREATE DATABASE bikerental" | mysql -uroot -psql
echo "CREATE DATABASE bikerental_it" | mysql -uroot -psql
echo "GRANT ALL ON bikerental.* TO 'wildfly'@'%'" | mysql -uroot -psql
echo "GRANT ALL ON bikerental_it.* TO 'wildfly'@'%'" | mysql -uroot -psql
echo "FLUSH PRIVILEGES" | mysql -uroot -psql

sudo sed -i "s/bind-address.*=.*127.0.0.1/bind-address = 0.0.0.0/g" /etc/mysql/my.cnf
sudo service mysql restart

# Now for a maven installation
$MAVEN_VERSION=3.3.9
$M2_HOME=/opt/apache-maven-${MAVEN_VERSION}
$MAVEN_TAR_FILE=apache-maven-${MAVEN_VERSION}-bin.tar.gz
$MAVEN_DOWNLOAD_URL=http://ftp-stud.hs-esslingen.de/pub/Mirrors/ftp.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/$MAVEN_TAR_FILE

wget $MAVEN_DOWNLOAD_URL

tar -xvf $MAVEN_TAR_FILE -C /opt/
rm $MAVEN_TAR_FILE

echo "export M2_HOME=${M2_HOME}" >> ~/.profile
echo 'export PATH=$M2_HOME/bin:$PATH' >> ~/.profile

source ~/.profile
