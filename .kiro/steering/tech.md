# Technology Stack

## Core Framework
- **Spring Boot 3.4.4** - Main application framework
- **Java 21** - Programming language and runtime
- **Maven** - Build system and dependency management

## Key Dependencies
- **Spring Data JPA** - Database abstraction and ORM
- **Spring Security** - Authentication and authorization
- **Spring Web** - REST API development
- **Spring Validation** - Input validation
- **Spring Actuator** - Application monitoring
- **Spring AMQP** - RabbitMQ integration
- **Spring Mail** - Email functionality

## Database
- **MySQL** - Primary database (production)
- **H2** - In-memory database (testing)
- **Hibernate** - ORM implementation

## Security & Authentication
- **JWT (JSON Web Tokens)** - Authentication mechanism
- **JJWT library** - JWT implementation

## Documentation & Testing
- **SpringDoc OpenAPI** - API documentation (Swagger UI)
- **Mockito** - Unit testing framework
- **Spring Boot Test** - Integration testing

## Message Queue
- **RabbitMQ** - Asynchronous message processing

## Common Commands

### Build & Run
```bash
# Build the project
./mvnw clean compile

# Run tests
./mvnw test

# Package application
./mvnw clean package

# Run application
./mvnw spring-boot:run

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Development
```bash
# Skip tests during build
./mvnw clean package -DskipTests

# Run in debug mode
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```