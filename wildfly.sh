#!/usr/bin/env bash

## WILDFLY

# Constants
readonly WILDFLY_VERSION=10.0.0.CR4
readonly WILDFLY_DIR=wildfly-$WILDFLY_VERSION
readonly WILDFLY_FILE=$WILDFLY_DIR.tar.gz
readonly WILDFLY_URL=http://download.jboss.org/wildfly/$WILDFLY_VERSION/$WILDFLY_FILE
readonly WILDFLY_MODULES_DIR=$WILDFLY_DIR/modules/system/layers/base

# Download and extract
if [ ! -d $WILDFLY_DIR ]; then
    wget $WILDFLY_URL
    tar -xvf $WILDFLY_FILE
    rm $WILDFLY_FILE
fi


## MYSQL

# Constants
readonly MYSQL_VERSION=5.1.37
readonly MYSQL_JAR_FILE=mysql-connector-java-$MYSQL_VERSION.jar
readonly MYSQL_DRIVER_URL=http://central.maven.org/maven2/mysql/mysql-connector-java/$MYSQL_VERSION/$MYSQL_JAR_FILE
readonly MYSQL_WILDFLY_DIR=$WILDFLY_MODULES_DIR/com/mysql/main

wget $MYSQL_DRIVER_URL

mkdir -p $MYSQL_WILDFLY_DIR
cp $MYSQL_JAR_FILE $MYSQL_WILDFLY_DIR
cp $WILDFLY_MODULES_DIR/com/h2database/h2/main/module.xml $MYSQL_WILDFLY_DIR
sed -i 's/name="com.h2database.h2"/name="com.mysql"/' $MYSQL_WILDFLY_DIR/module.xml
sed -i "s/<resource-root path=\".*\"\/>/<resource-root path=\"${MYSQL_JAR_FILE}\"\/>/" $MYSQL_WILDFLY_DIR/module.xml/module.xml

# Clean up
rm $MYSQL_JAR_FILE


$WILDFLY_DIR/bin/jboss-cli.sh --commands=\
'embed-server --server-config=standalone-full.xml',\
'/subsystem=messaging-activemq/server=default:write-attribute(name=journal-type,value=NIO)',\
'/subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql,driver-class-name=com.mysql.jdbc.Driver,xa-datasource-class=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource,driver-name=mysql)',\
'/subsystem=datasources/data-source=BikerentalDS:add(connection-url=jdbc:mysql://localhost/bikerental,driver-name=mysql,jndi-name=java:/datasources/BikerentalDS,use-java-context=true,user-name=wildfly,password=wildfly,enabled=true)'

sudo chown -R vagrant $WILDFLY_DIR
