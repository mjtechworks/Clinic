[![Build Status](https://travis-ci.org/Daniel194/Clinical-Management.svg?branch=master)](https://travis-ci.org/Daniel194/Clinical-Management) [![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.clinical.management)](https://sonarcloud.io/dashboard/index/com.clinical.management) [![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/Daniel194/Clinical-Management/blob/master/LICENCE)

# Clinical-Management

This is a proof-of-concept application, which demonstrates Microservice Architecture Pattern using Spring Boot, Spring Cloud and Docker.

## Technologies used:
- Backend : Java 8, Gradle 4.2, Srping Boot 1.5.8, Spring Cloud Dalston.SR4, Spring Security, Spring Data JPA
- Frontend : Angular 5, TypeScript 2.4, NPM 5.5.
- Containerization : Docker.
- Database : MongoDB 3.4.
- Infrastructure : GitHub, SonarQube, TravisCI.

## Functional Service

Clinical Management was decomposed into three core microservices. All of them are independently deployable applications, organized around certain business domains.

#### 1. Doctor Service

Contains general inforamtion about a doctor: email, location, name, phone numbert, etc. 

#### 2. Patients Service

Contains general inforamtion about a patient: email, name, phone number, etc.

#### 3. Appointment Service

Contains inforamtion about an appointment between a doctor and a patient : doctor id, patient id, star date and end date of the appointment, description, etc. 

This service required information from other services and sends e-mail messages to the patient when an appoitment is established, when an appoitment is closed by the doctor or when an appoitment was finished.

Also this microservices expose an endpoint to retrieve weather forecast for the next five days.

### Notes :
- Each microservice has it's own database.
- In this project, I use MongoDB as a primary database for each service.
- Service-to-service communication is quite simplified: microservices talking using only synchronous REST API.
- The front end part of this application has its own container.
- The user interface was written with Angular 5.

## How to run all the things?

#### Before you start

- Install Docker, Docker Compose, Java 8, Gradle, and NPM.
- Make sure to build the project: `gradle build`
- Also you have to build the user-interface service. Run the command `npm run-script build` inside the user-interface project.

#### Run the project
- From the commande line run `bash start_dev.sh` and waith for aproximately 5 minutes until all the project is containerized and started.
- Keep in mind, that you are going to start 8 Spring Boot applications, 4 MongoDB instances and an Angular interface. Make sure you have 4 Gb RAM available on your machine.

#### Important endpoints
- http://localhost:80 - Gateway
- http://localhost:8761 - Eureka Dashboard
- http://localhost:9000/hystrix - Hystrix Dashboard (paste Turbine stream link on the form)
- http://localhost:8989 - Turbine stream (source for the Hystrix Dashboard)
- http://localhost:4000 - User Interface application.
