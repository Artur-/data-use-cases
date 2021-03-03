#!/bin/bash

mvn clean install -Pproduction && heroku container:push web -a grid-with-aggregated-data && heroku container:release web -a grid-with-aggregated-data
