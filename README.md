# HealthCare-Web-Project

This is a healthcare management system project developed for the SoftUni Spring Advanced 2024 course.

## Table of Contents
- [About](#about)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [REST API](#rest-api)

## About
This project aims to create a comprehensive healthcare management system, providing functionalities to manage patient records, appointments, and medical staff information.

## Features
- Patient management
- Appointment scheduling
- Medical staff management
- Secure login and authentication

## Technologies
- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java (Spring Boot)
- **Database:** MySQL (for production)
- **Build Tool:** Gradle

## Installation
1. [DOWNLOAD](https://github.com/stilyanov/HealthCare-Web-Project/archive/refs/heads/main.zip) the repo.
2. [DOWNLOAD](https://github.com/stilyanov/Healthcare-Appointments/archive/refs/heads/main.zip) the API for appointments.
3. Set up environment variables in both of the projects for mysql
    - username: ${db_username}
    - password: ${db_password}

## Usage
Here you can find information about the roles and what they do! There are 3 roles ADMIN, PATIENT and DOCTOR
<h3>Admin</h3>

Only users with role ADMIN can access the admin-panel to view patients,doctors,appointments and contacts

<img src="https://github.com/stilyanov/HealthCare-Web-Project/blob/main/src/main/resources/static/images/project/admin.png" max-width=100% />

Also to add doctor

<img src="https://github.com/stilyanov/HealthCare-Web-Project/blob/main/src/main/resources/static/images/project/admin-add-doctor.png" max-width=100% />

<h3>Doctor</h3>

Doctors can view their appointments and delete them!

<img src="https://github.com/stilyanov/HealthCare-Web-Project/blob/main/src/main/resources/static/images/project/doctor.png" max-width=100% />

Doctors also can add results from appointments to users, so users can view their results

<img src="https://github.com/stilyanov/HealthCare-Web-Project/blob/main/src/main/resources/static/images/project/doctor-result.png" max-width=100% />

<h3>Users</h3>

Users Can view their appointments and results from doctors

<img src="https://github.com/stilyanov/HealthCare-Web-Project/blob/main/src/main/resources/static/images/project/user-appointments.png" max-width=100% />

<img src="https://github.com/stilyanov/HealthCare-Web-Project/blob/main/src/main/resources/static/images/project/user-results.png" max-width=100% />

## Rest Api

The REST API for managing appointments is available in the [Healthcare-Appointments repository](https://github.com/stilyanov/Healthcare-Appointments). Here are the main endpoints:

- GET /appointments/book/{doctorId}: Retrieve available appointment times for a specific doctor on a given date.
- GET /appointments/user/{userId}: Retrieve all appointments for a specific user.
- GET /appointments/{appointmentId}: Retrieve details of a specific appointment by its ID.
- GET /appointments/patient/{patientId}: Retrieve all appointments for a specific patient.
- GET /appointments/doctor/{doctorId}: Retrieve all appointments for a specific doctor.
- GET /appointments/all: Retrieve all appointments with full information.
- POST /appointments/book/{doctorId}: Book a new appointment with a doctor.
- DELETE /appointments/delete/{id}: Delete an appointment by its ID.
