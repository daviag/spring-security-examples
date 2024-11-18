# Spring Security Examples

This repository contains some basic examples of Spring Security usage.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Subprojects](#subprojects)
  - [basic-auth](#basic-auth)
  - [jwt-auth](#jwt-auth)
- [Contributing](#contributing)
- [Additional Resources](#additional-resources)

## Technologies Used
- [Gradle 8](https://gradle.org/)
- [Java 21](https://www.java.com/en/)
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Spring Security 6](https://spring.io/projects/spring-security)
- [H2](https://h2database.com/)
- [JUnit](https://junit.org/)
- [Mockito](https://site.mockito.org/)
- [Postman](https://www.postman.com/)

## Project Structure

The project is structured as follows:

```bash  
.
├── basic-auth
└── jwt-auth
```
- [basic-auth](#basic-auth) - Example of how to use basic authentication using Spring Security
- [jwt-auth](#jwt-auth) - Example of how to use JWT authentication using Spring Security

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository 
    ```bash 
    git clone https://github.com/daviag/spring-security-examples.git
    ```
2. Navigate to the project directory 
    ```bash 
    cd spring-security-examples
    ```
3. Build the whole project
    ```bash 
    cd ./gradlew build
    ```
4. Execute tests
    ```bash 
    ./gradlew test
    ```
5. Run a specific subproject
    ```bash 
    ./gradlew <subproject>:bootRun
    ```

## Subprojects
Each subproject contains an example of how to use a specific authentication method using Spring Security

### basic-auth
Example of how to use basic authentication using Spring Security. This project validates the user against a H2 database.
There is a predefined user with username "admin" and password "admin".

#### Endpoints

| Method | Path                        | Description                                           |
| --- |-----------------------------|-------------------------------------------------------|
| GET | /api/v1/greeting/public     | Public endpoint. No authentication is needed          |
| GET | /api/v1/greeting/restricted | Restricted endpoint. Basic authentication is required |

#### Usage

To access the public endpoint:

```bash
curl http://localhost:8080/api/v1/greeting/public
```

To access the restricted endpoint:

```bash
curl -u admin:admin http://localhost:8080/api/v1/greeting/restricted
```

### jwt-auth
Example of how to use JWT authentication using Spring Security.

This project uses JWT authentication with a predefined secret key. The secret key is a Base64 encoded string 
stored in the application.yml file. This secret key is used to sign the JWT tokens. 

Additionally, this project uses a refresh token to generate new access tokens. 
The refresh token is stored in the database and is used to generate new access tokens. 

#### Endpoints

| Method | Path                        | Description                                           |
| --- |-----------------------------|-------------------------------------------------------|        
| GET | /api/v1/greeting/public     | Public endpoint. No authentication is needed          |
| GET | /api/v1/greeting/restricted | Restricted endpoint. JWT authentication is required |
| GET | /api/v1/auth/register      | Endpoint to register a new user                       |
| GET | /api/v1/auth/login         | Endpoint to login a user                              |
| GET | /api/v1/auth/refresh       | Endpoint to refresh the access token                  |

#### Usage

To access the public endpoint:

```bash
curl http://localhost:8080/api/v1/greeting/public
```

To access the restricted endpoint:

```bash
curl http://localhost:8080/api/v1/greeting/restricted \
  -H "Authorization: Bearer <token>"
```

To register a new user:

```bash
curl -X POST http://localhost:8080/api/v1/auth/register \ 
  -H "Content-Type: application/json" \ 
  -d '{"firstName": "userFirstName", "lastName": "userLastName", "email": "user@email", "password": "password", "roles": ["USER"]}' 
```

To login a user:

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \ 
  -H "Content-Type: application/json" \ 
  -d '{"username": "user@email", "password": "password"}'
```

To refresh the access token:

```bash
curl -X POST http://localhost:8080/api/v1/auth/refresh \ 
  -H "Content-Type: application/json" \ 
  -d '{"token": "<your_refresh_token>"}'
```

#### TODOs
- [ ] Email the user upon registration to verify their account

## Contributing

Contributions are welcome! Please follow the these steps to contribute to this project:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`.
3. Make your changes.
4. Push your branch: `git push origin feature-name`.
5. Create a pull request.


