# Protection against covid
<p align="center">
  <img style="width:25%;" src="https://github.com/suraj-996/lying-powder-9405/blob/main/images/b%20(2).png" />
</p>

## ER-Diagram
![ER_diagram](https://user-images.githubusercontent.com/96117548/208404656-8473c0c5-da53-4021-99b8-c7cc8829658f.jpg)

# REST API for Protection against Covid Application

* This project has been developed by a team of 4 Java Back-End developers during our project week in Masai School. 
* This REST-API will facilitate the citizen with an option to register and schedule the vaccination session online in vaccination centers of their choice.
* The Citizen self-registration module will ensure fool-proof identification of deserving candidates for receiving the vaccines.
* The objective is to handhold the citizens to register and schedule an appointment for vaccination

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger

## Modules

* Login, Logout Module
* User Module
* Admin Module

## Features

* User and Admin authentication & validation with session uuid having.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid session token can add/update/delete driver or customer from main database
    * Admin can access the details of different Member ,Vaccine Center ,Vaccine Inventory and Vaccine Ragistration.
* User Features:
    * A user can register himself or herself on the platform.
    * He/She can check the vaccine centres and vaccine availabilty.
    * After booking an appointment, he will get appointment details for the vaccine dose.    


## Contributors

* [@Afzhal Ahmed S](https://github.com/Afzhal-ahmed-s)
* [@Arijit Bhatt](https://github.com/ARIJIT321)
* [@Rajul Tiwari](https://github.com/Rajul-Tiwari)
* [@Arijeet Chowdry](https://github.com/arijeet8008)


## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/suraj-996/lying-powder-9405/blob/main/Covid-19-VaccineScheduler/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8888
    spring.datasource.url=jdbc:mysql://localhost:3306/vaccinescheduler;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

## API Root Endpoint

`https://localhost:8888/`

`http://localhost:8888/swagger-ui/`

