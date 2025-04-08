
# Clynix - Backend

Clynix Backend is the server-side part of the Clynix platform, responsible for handling medical appointment bookings, managing clinics and specialists, and providing secure authentication via **JWT**. The backend is built with **Spring Boot**, **Spring Security**, and **MySQL**, using **JPA** for data persistence and **Lombok** to simplify the codebase. Email notifications are handled with **Spring Mail**.

## Technologies Used

- **Spring Boot**: A Java framework for building production-ready applications.
- **Spring Security**: Provides security and authentication using **JWT** tokens.
- **JWT (JSON Web Token)**: Used for secure authentication and authorization.
- **MySQL**: A relational database management system for storing application data.
- **JPA (Java Persistence API)**: Used for managing and mapping data between Java objects and MySQL.
- **Lombok**: A library that helps reduce boilerplate code in Java (e.g., getters, setters, constructors).
- **Spring Mail**: A framework for sending emails from the application.

## Prerequisites

- **Java 17+** installed on your machine.
- **MySQL** installed and running.
- **Maven** or **Gradle** (depending on the project setup).

## Installation

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd clynix-backend
   ```

2. Set up your **MySQL** database:

   - Create a new database for Clynix (e.g., `clynix_db`).
   - Update the `application.properties` (or `application.yml`) file with your MySQL credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/clynix_db
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   ```

3. Build the application:

   Using **Maven**:
   
   ```bash
   mvn clean install
   ```

   Using **Gradle**:
   
   ```bash
   ./gradlew build
   ```

4. To run the application locally in development:

   ```bash
   mvn spring-boot:run
   ```

   or

   ```bash
   ./gradlew bootRun
   ```

5. If running the application for the first time, ensure you have initialized the database schema using **JPA** (automatic schema generation should be enabled in the properties file).
