# Clynix - Backend

This is the backend for **Clynix**, a simple web app that allows patients to choose consultations with doctors. It was built using **Spring Boot**, **JPA**, and **MySQL**, with optional features like **Spring Security** and **JWT authentication** added later.

Only the backend logic is included in this repo, as it's the only part I'm allowed to share.

## Tech Stack

- Java 17, Spring Boot, JPA, MySQL  
- Spring Security + JWT (optional)  
- Lombok, Spring Mail

## Setup

1. Clone the repo and create your own `application.properties` file with:

   ```properties
   spring.datasource.url=jdbc:mysql:///clinyxx?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
   spring.datasource.username=
   spring.datasource.password=

   spring.application.name=clinyx

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=
   ```

2. Run using Maven or Gradle:

   ```bash
   mvn spring-boot:run
   # or
   ./gradlew bootRun
   ```
