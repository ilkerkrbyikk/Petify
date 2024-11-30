# Petify

Petify is a platform that facilitates interaction between pet owners and pet caregivers. Users can use this application to rehome their pets, make hotel reservations, access pet grooming services, and more. 

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

- **Re-Homing Announcements**: Pet owners can create and manage announcements to find new homes for their pets.
- **Reservation Management**: Users can book and manage reservations for pet hotels and grooming services.
- **Comment and Rating System**: Users can leave feedback and ratings for services received, helping others make informed decisions.

## Technologies Used

- **Java**: The primary programming language for backend development.
- **Spring Boot**: A framework for building the RESTful API.
- **JPA/Hibernate**: For object-relational mapping and database interactions.
- **MySQL**: The relational database used to store application data.
- **Lombok**: A library to reduce boilerplate code in Java classes.
- **Spring Security**: For securing the application and managing user authentication.


## Installation

To set up the project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/petify.git
   cd petify

2. **Set up the database**:
- Create a MySQL database named petify.

- Update the src/main/resources/application.properties file with your database credentials:

   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/petify
   spring.datasource.username=your_username
   spring.datasource.password=your_password

3. **Build and run the application**.
   ```bash
      ./mvnw clean install

4. **Access the application**:
   ```bash
      ./mvnw spring-boot:run


## **API Endpoints**

  Petify API includes Swagger API documentation. You can find everything from here:  http://localhost:8080/swagger-ui/index.html. 
  Here is the endpoints.



![bred](https://github.com/user-attachments/assets/5cd05dab-fe61-4c7c-b3ca-334226317547)
![city](https://github.com/user-attachments/assets/97268e19-5d30-4aa7-b2fb-6da3001f0525)
![comment](https://github.com/user-attachments/assets/df90e98b-1f54-47ac-81da-5157d97a317c)
![corporate customer](https://github.com/user-attachments/assets/f75b44a4-c4db-4832-bdb7-624829dc46ef)
![customer](https://github.com/user-attachments/assets/798266dd-f75e-4e78-8b0e-d3efa77c19f5)
![gender](https://github.com/user-attachments/assets/95d25db2-8e24-40f8-a569-1f5bbd0bc712)
![hotel](https://github.com/user-attachments/assets/0e65f525-6f39-46c1-8335-875ee124b644)
![pet barber reservations](https://github.com/user-attachments/assets/f4bea3e6-33d5-45f3-824d-8078143eda95)
![pet barber](https://github.com/user-attachments/assets/b20d5fbb-f90e-4583-8e85-801767fa8bd3)
![pet sitter](https://github.com/user-attachments/assets/8a959de3-0bda-4c9d-a95d-c7ec265c5d65)
![pet](https://github.com/user-attachments/assets/21231508-dffe-4bba-8be5-041999de0a54)
![re homing](https://github.com/user-attachments/assets/4fba0b68-0e6f-488e-800f-dd2cbbb47890)
![admin auth](https://github.com/user-attachments/assets/6edbab17-5ece-4cfa-b0f6-8fd4d25f0d93)
![corporate customer auth](https://github.com/user-attachments/assets/a900ddab-8771-4a12-a5a9-dfb5dd2d0d01)
![customer auth](https://github.com/user-attachments/assets/9933e2f0-e81d-40a0-97d7-35d988b22bd4)
![pet sitter auth](https://github.com/user-attachments/assets/5c31de37-f21f-4bcc-b2f7-aa30e4826c0c)

   

## *Roadmap*

### Phase 1: Core Enhancements
- [ ] Implement two-factor authentication for enhanced security.
- [ ] Allow users to upload images for pets, pet sitters, and hotels.
- [ ] Enable viewing of past reservations for better user experience.
- [ ] Complete API documentation to standardize and streamline integrations.

### Phase 2: New Features
- [ ] Add veterinary services booking to expand platform capabilities.
- [ ] Provide personalized recommendations for pet services using AI.
- [ ] Introduce dynamic notifications for upcoming reservations and updates.

### Phase 3: Optimization
- [ ] Refactor code for improved performance and maintainability.
- [ ] Refactor some codes for improve user experience.


  

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

  
## Contributors

Contributions are welcome! If you would like to contribute to the project, please follow these steps:

1 - Fork the repository.

2- Create a new branch (git checkout -b feature/YourFeature).

3- Make your changes and commit them (git commit -m 'Add some feature').

4- Push to the branch (git push origin feature/YourFeature).

5- Open a pull request.



  
## Contact

For any questions or suggestions, please reach out to ilkerkubilaykarabiyik@gmail.com.

  
