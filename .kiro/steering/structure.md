# Project Structure

## Root Directory
```
├── src/                    # Source code
├── uploads/               # Static file uploads (served by Spring)
├── pom.xml               # Maven configuration
├── mvnw, mvnw.cmd        # Maven wrapper scripts
└── .kiro/                # Kiro IDE configuration
```

## Source Structure
```
src/
├── main/
│   ├── java/com/example/Tozin_Solutions_back_end/
│   │   ├── TozinSolutionsBackEndApplication.java  # Main Spring Boot class
│   │   ├── V1/                                    # Version 1 API (Layered Architecture)
│   │   │   ├── config/                           # Configuration classes
│   │   │   ├── controller/                       # REST controllers
│   │   │   ├── dto/                             # Data Transfer Objects
│   │   │   ├── enums/                           # Enumeration classes
│   │   │   ├── model/                           # JPA entities
│   │   │   ├── repository/                      # Data access layer
│   │   │   └── service/                         # Business logic layer
│   │   └── V2/                                   # Version 2 API (Domain-Driven Design)
│   │       ├── core/                            # Domain layer
│   │       └── infrastructure/                  # Infrastructure layer
│   └── resources/
│       └── application.properties               # Application configuration
└── test/
    └── java/                                    # Test classes
```

## Architecture Patterns

### V1 - Traditional Layered Architecture
- **Controller Layer**: REST endpoints and request handling
- **Service Layer**: Business logic and transaction management
- **Repository Layer**: Data access using Spring Data JPA
- **Model Layer**: JPA entities representing database tables
- **DTO Layer**: Data transfer objects for API contracts

### V2 - Domain-Driven Design (DDD)
- **Core**: Domain entities, value objects, and business rules
- **Infrastructure**: External concerns (database, messaging, etc.)

## File Upload Structure
```
uploads/
└── sofas/                # Product images organized by category
    └── [timestamp]_[filename].jpg
```

## Naming Conventions
- **Packages**: lowercase with underscores (following existing pattern)
- **Classes**: PascalCase
- **Methods/Variables**: camelCase
- **Constants**: UPPER_SNAKE_CASE
- **Database**: snake_case (tozin_solutions)

## Configuration Notes
- Static resources served from `uploads/` directory
- JWT tokens configured with custom secret and validity period
- MySQL for production, H2 for testing
- File uploads limited to 40MB
- RabbitMQ and SMTP configured for messaging