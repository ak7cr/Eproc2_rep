# Eproc2_rep

# Project Overview

This project is being developed using **Spring Boot**, a popular framework for building robust and scalable Java applications. Spring Boot simplifies the development of enterprise applications by providing pre-configured templates, powerful dependency management, and production-ready features.

## Features
- Modular and scalable architecture.
- Integration with Spring Web, Spring Data JPA, and MySQL.
- Built-in validation and security features.

## Prerequisites
Before running this project, ensure the following are installed:

1. **Java Development Kit (JDK)** (version 11 or higher).
2. **Maven** (for dependency management).
3. **MySQL** (for database).

## Running the Project

1. Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd <project-directory>
   ```

3. Run the project using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

## Database Configuration

This project uses **MySQL** for database management. Update the `application.properties` or `application.yml` file to match your local database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Handling Line Endings
To ensure consistent line endings across platforms, add the following to a `.gitattributes` file in the root of your project:

```plaintext
# Ensure all text files use LF line endings
* text=auto
```

This ensures that line endings are converted to LF on commit, avoiding CRLF issues.
