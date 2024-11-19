# Petify

Petify is a platform that allows pet owners to book services such as pet sitting, barber, re-homing, and hotels. Built with Java Spring, it offers a seamless experience for reserving pet-related services with real-time availability.

## Features

- **Pet Sitting**: Book a pet sitter based on availability.
- **Pet Barber**: Schedule grooming sessions for pets.
- **Re-Homing**: Connect pets with new homes.
- **Pet Hotels**: Reserve stays for pets in a comfortable environment.
  
## Tech Stack

- **Java** (Spring Boot)
- **Spring Security**
- **Hibernate / JPA**
- **MySQL**

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- MySQL

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/ilkerkrbyikk/Petify.git

2. Create a database named petify_db
   - Update application.properties with your credentials.
   ```bash
       spring.datasource.url=jdbc:mysql://localhost:3306/petify
       spring.datasource.username=YOUR_USERNAME
       spring.datasource.password=YOUR_PASSWORD

3. Build and run the application.
   ```bash
      mvn spring-boot:run

4.Access the application:

    Navigate to http://localhost:8080 in your browser.

   
