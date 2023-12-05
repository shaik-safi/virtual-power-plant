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
   - **Endpoint:** [http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/power-plant/battery-statistics?start=0000&end=5000](http://virtualpowerplantaws-env.eba-mnuzr5jy.us-east-1.elasticbeanstalk.com/power-plant/battery-statistics?start=0000&end=5000)
   - **Method:** GET
   - **Description:** Retrieves statistics for batteries within a given postcode range.

   Use the above link with sample data (start=0000, end=5000) to explore meaningful statistics.

   **Note:** To retrieve statistics for a specific postcode range, replace `<start>` and `<end>` with the desired values.

## Portfolio

Check out my portfolio: [https://shaiksafi.netlify.app/](https://shaiksafi.netlify.app/)

## Contributing

Contributions are welcome! If you find a bug or have a feature request, please open an issue.

## License

This project is licensed under the [MIT License](LICENSE).
