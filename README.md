# Virtual Power Plant System

## Overview

This project implements a virtual power plant system for aggregating distributed power sources into a single cloud-based energy provider. It provides a REST API built with Spring Boot that allows users to manage batteries and retrieve statistics for batteries within a given postcode range.

## Deployment

The application is deployed on AWS using Elastic Beanstalk for the application layer and RDS for the database layer. The deployment details are as follows:

- **Application URL:** [http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/](http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/)

## Endpoints

1. **Add Batteries:**
   - **Endpoint:** [http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/power-plant/batteries](http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/power-plant/batteries)
   - **Method:** POST
   - **Description:** Adds a list of batteries to the virtual power plant system.

2. **Battery Statistics:**
   - **Endpoint:** [http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/power-plant/battery-statistics](http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/power-plant/battery-statistics)
   - **Method:** GET
   - **Description:** Retrieves statistics for batteries within a given postcode range.

## Portfolio

Check out my portfolio: [https://shaiksafi.netlify.app/](https://shaiksafi.netlify.app/)
