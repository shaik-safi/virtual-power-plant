
# Virtual Power Plant System

The Virtual Power Plant System is a Spring Boot application that provides a REST API for managing a virtual power plant with distributed batteries. It allows adding batteries to the system, retrieving battery statistics within a postcode range, and fetching all batteries in the system.

## AWS Hosting

The application is hosted on Amazon Web Services (AWS), providing a scalable and reliable infrastructure for seamless access. You can access the live deployment of the Virtual Power Plant System by following the link below:

[Access Virtual Power Plant on AWS](http://virtual-power-plant-aws-env.eba-m2igmwzg.us-east-1.elasticbeanstalk.com/power-plant/batteries)

## Getting Started

### Prerequisites

-   Java Development Kit (JDK) 11 or later
-   Maven (for building the project)

### Installation

1.  Clone the repository:
`git clone https://github.com/your-username/virtual-power-plant.git
cd virtual-power-plant` 

2.  Build the project:
`mvn clean install` 

3.  Run the application:
`mvn spring-boot:run` 

The application will be accessible at `http://localhost:8080`.

## Usage

### Adding Batteries

Use the following endpoint to add batteries to the system:

`POST /power-plant/batteries` 

Request Body:

`[
  {
    "name": "Battery1",
    "postcode": "560060",
    "wattCapacity": 1000
  },
  {
    "name": "Battery2",
    "postcode": "560100",
    "wattCapacity": 2000
  }
]` 

### Retrieving Batteries

Use the following endpoint to retrieve all batteries in the system:

`GET /power-plant/batteries` 

### Battery Statistics

Use the following endpoint to retrieve battery statistics within a postcode range:

`GET /power-plant/battery-statistics?startPostcode=10000&endPostcode=20000` 

## Endpoints

-   `POST /power-plant/batteries`: Add batteries to the system.
-   `GET /power-plant/batteries`: Retrieve all batteries.
-   `GET /power-plant/battery-statistics`: Retrieve battery statistics within a postcode range.

## Testing

The project includes unit tests for the `PowerPlantServiceImpl` class. To run the tests, execute:

`mvn test` 