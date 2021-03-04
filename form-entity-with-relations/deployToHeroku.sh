#!/bin/bash

APP=$(basename `pwd`)
mvn clean install -Pproduction && heroku container:push web -a $APP && heroku container:release web -a $APP
