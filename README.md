[![Build Status](https://travis-ci.org/Daniel194/Clinical-Management.svg?branch=master)](https://travis-ci.org/Daniel194/Clinical-Management) [![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.clinical.management)](https://sonarcloud.io/dashboard/index/com.clinical.management) [![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/Daniel194/Clinical-Management/blob/master/LICENCE)

# Clinical-Management

This is a proof-of-concept application, which demonstrates Microservice Architecture Pattern using Spring Boot, Spring Cloud and Docker.

## Technologies used:
- Backend : Java 8, Gradle, Srping Boot, Spring Cloud, Spring Security, Spring Data JPA
- Frontend : Angular 5, TypeScript, NPM.
- Containerization : Docker.
- Database : MongoDB.
- Infrastructure : GitHub, DockerHub, SonarQube, TravisCI.

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

- Install Docker, Docker Compose, Java 8 and Gradle.
- Make sure to build the project: `gradle build`

#### Production mode
In this mode, all latest images will be pulled from Docker Hub.
Just copy `docker-compose.yml` and hit `docker-compose up`

#### Development mode
If you'd like to build images yourself, you have to clone all repository and build artifacts with gradle. Then, run `docker-compose -f docker-compose.yml -f docker-compose.dev.yml up`

#### Important endpoints
- http://localhost:80 - Gateway
- http://localhost:8761 - Eureka Dashboard
- http://localhost:9000/hystrix - Hystrix Dashboard (paste Turbine stream link on the form)
- http://localhost:8989 - Turbine stream (source for the Hystrix Dashboard)
- http://localhost:4000 - User Interface application.
