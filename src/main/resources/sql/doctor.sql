CREATE DATABASE Doctor;

CREATE TABLE doctor (
  id        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username  VARCHAR(32)     NOT NULL UNIQUE,
  firstName VARCHAR(32)     NOT NULL,
  lastName  VARCHAR(32)     NOT NULL,
  password  VARCHAR(320)    NOT NULL
);

CREATE TABLE patient (
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  doctorID    INT             NOT NULL,
  firstName   VARCHAR(32)     NOT NULL,
  lastName    VARCHAR(32)     NOT NULL,
  dob         DATE            NOT NULL,
  address     VARCHAR(320)    NOT NULL,
  phoneNumber VARCHAR(10)     NOT NULL,
  CONSTRAINT FOREIGN KEY (doctorID) REFERENCES doctor (id)
);
