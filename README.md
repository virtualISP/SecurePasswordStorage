# SecurePasswordStorage

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Table of Contents

- [About](#about)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## About

SecurePasswordStorage is a Java Spring Boot application designed to securely store and manage user passwords. The application provides endpoints for creating users, logging in and changing passwords.

## Features

- Create a new user account
- User login with validation
- Change user password
- Secure password storage using hashing
- Hashing with Salting implementation
  
## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- Git

## Installation

1. **Clone the repository**
    ```sh
    git clone https://github.com/virtualISP/SecurePasswordStorage.git
    cd SecurePasswordStorage
    ```

2. **Build the project**
    ```sh
    mvn clean install
    ```

3. **Run the application**
    ```sh
    mvn spring-boot:run
    ```

## Usage

After running the application, you can access the endpoints via any API client like Postman or can interact with the beautiful frontend interface prodided for the same.

## API Endpoints

### Create User

- **URL:** `/user/create`
- **Method:** `POST`
- **Parameters:**
    - `username`: String
    - `password`: String
- **Example Request:**
    ```json
    {
        "username": "john",
        "password": "password123"
    }
    ```
- **Example Response:**
    ```json
    {
        "message": "User created successfully"
    }
    ```

### Login User

- **URL:** `/user/login`
- **Method:** `POST`
- **Parameters:**
    - `username`: String
    - `password`: String
- **Example Request:**
    ```json
    {
        "username": "john",
        "password": "password123"
    }
    ```
- **Example Response:**
    ```json
    {
        "message": "Login successful"
    }
    ```

### Change Password

- **URL:** `/user/changePassword`
- **Method:** `POST`
- **Parameters:**
    - `username`: String
    - `oldPassword`: String
    - `newPassword`: String
- **Example Request:**
    ```json
    {
        "username": "john",
        "oldPassword": "password123",
        "newPassword": "newPassword123"
    }
    ```
- **Example Response:**
    ```json
    {
        "message": "Password changed successfully"
    }
    ```


## Testing

You can test the functionalities using Postman or any other API client.

1. **Create a new user**:
    - Send a POST request to `/user/create` with `username` and `password` as parameters.
2. **Login a user**:
    - Send a POST request to `/user/login` with `username` and `password` as parameters.
3. **Change a user's password**:
    - Send a POST request to `/user/changePassword` with `username`, `oldPassword`, and `newPassword` as parameters.

## Contributing

Contributions are welcome! Please create a pull request or raise an issue to discuss any changes.

1. Fork the repository
2. Create a new feature branch (`git checkout -b feature/feature-name`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/feature-name`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
