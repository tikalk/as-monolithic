#!/bin/bash

echo "Starting FleetTracker Monolithic"

DIRNAME=`dirname $0`
APP_HOME=`cd $DIRNAME/..;pwd;`
export APP_HOME;

java -Duser.timezone="UTC" -jar $APP_HOME/build/libs/as-monolithic.jar