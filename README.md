# SRE Student API

Inspired by the exercise from the [SRE Bootcamp](https://playbook.one2n.in/sre-bootcamp/sre-bootcamp-exercises/1-create-a-simple-rest-api)

## Project Architecture and Design Strategy
The project is based on the **Hexagonal Architecture** organized with packaged not modules.

## Functional Requirements
- Add a new student.
- Get all students.
- Get a student with an ID. 
- Update existing student information. 
- Delete a student record.

## Running This Project

### Prerequisites

Before running this project, ensure that you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven (for building and managing dependencies)

### Getting Started

Follow these steps to get the project up and running on your local machine:

1. **Clone the Repository**:
   ```shell
   git clone https://github.com/jonamarkin/student-management-api.git
   cd student-management-api
   
2. **Build The Project**
    ```shell
   mvn clean install
   
3. **Run The Project**
    ```shell
   mvn spring-boot:run

### Using MakeFile
Alternatively you can simply make use of the **Makefile** to start the project by running the following command in the root directory
```shell
make run
```

## Accessing The API

1. You can access the Swagger documentation of the API Via
   ```shell
   http://localhost:8080/swagger-ui/index.html
   ```
2. [Postman Documentation](https://documenter.getpostman.com/view/26444770/2sA2xiWrmY)
   

## Database
This project uses an H2 in-memory database. 
You can access the H2 console at `http://localhost:8080/h2-console` when the application is running. 
The JDBC URL is `jdbc:h2:mem:studentdb`.
Username and password are both `student`.

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests to make improvements.