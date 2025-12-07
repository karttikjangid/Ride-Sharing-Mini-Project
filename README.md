# Ride Sharing Application

A simple ride-sharing platform built with Spring Boot and MongoDB that connects users who need rides with available drivers.

## Features

### For Users
- Create ride requests with pickup and drop locations
- View all your ride history
- Mark rides as completed

### For Drivers
- View all pending ride requests
- Accept available rides
- Complete rides after drop-off

### Security
- JWT-based authentication
- Role-based access control (USER/DRIVER)
- Secure password encryption using BCrypt

## Tech Stack

- **Backend**: Spring Boot
- **Database**: MongoDB
- **Security**: Spring Security with JWT
- **Build Tool**: Maven/Gradle

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user/driver
- `POST /api/auth/login` - Login and get JWT token

### User Endpoints
- `POST /api/v1/rides` - Create a new ride request
- `GET /api/v1/user/rides` - Get all your rides
- `PUT /api/v1/rides/{rideId}/complete` - Complete a ride

### Driver Endpoints
- `GET /api/v1/driver/rides/requests` - View pending ride requests
- `PUT /api/v1/driver/rides/{rideId}/accept` - Accept a ride

## Getting Started

### Prerequisites
- Java 17 or higher
- MongoDB installed and running
- Maven or Gradle

### Configuration

Create an `application.properties` file in `src/main/resources/`:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/rideshare
spring.data.mongodb.database=rideshare

# JWT Configuration
jwt.secret=your-secret-key-here
jwt.expiration-ms=86400000
```

### Running the Application

```bash
# Using Maven
mvn spring-boot:run

# Using Gradle
gradle bootRun
```

The application will start on `http://localhost:8080`

## Usage Example

### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john",
    "password": "password123",
    "role": "USER"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john",
    "password": "password123"
  }'
```

### 3. Create a Ride (with token)
```bash
curl -X POST http://localhost:8080/api/v1/rides \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "pickupLocation": "123 Main St",
    "dropLocation": "456 Park Ave"
  }'
```

## Project Structure

```
RideShare/
├── config/           # Security and JWT configuration
├── controller/       # REST API endpoints
├── dto/              # Data transfer objects
├── model/            # Entity models
├── repository/       # MongoDB repositories
├── service/          # Business logic
└── util/             # Utility classes
```

## Ride States

- **REQUESTED** - User created a ride request
- **ACCEPTED** - Driver accepted the ride
- **COMPLETED** - Ride finished successfully

## Author

Built as a learning project to understand Spring Boot, MongoDB, and JWT authentication.

## License

This project is open source and available for educational purposes.
