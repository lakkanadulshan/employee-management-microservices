# Employee Management Microservices Architecture

An enterprise-grade, distributed **Employee Management System** built with **Java 21, Spring Boot 3.3.4, Spring Cloud 2023.0.3, and MySQL**.

This project demonstrates modern microservices architecture, centralized configuration management, dynamic service discovery, API gateway routing, inter-service communication, fault tolerance, and distributed tracing using Zipkin and Micrometer.

## 🏛️ System Architecture

```text
                         ┌───────────────────────────┐
                         │   Spring Cloud Config     │
                         │        Server :8888       │
                         │     (Remote Git Repo)     │
                         └─────────────┬─────────────┘
                                       │
                                       ▼
                         ┌───────────────────────────┐
                         │        API Gateway        │
                         │          :9191            │
                         └─────────────┬─────────────┘
                                       │
                ┌──────────────────────┼──────────────────────┐
                │                      │                      │
                ▼                      ▼                      ▼
       ┌─────────────────┐   ┌─────────────────┐   ┌─────────────────┐
       │ Employee Service│   │Department Service│  │Organization Svc.│
       │     :8084       │   │     :8083        │  │     :8086       │
       └────────┬────────┘   └─────────────────┘   └─────────────────┘
                │                      ▲                      ▲
                │                      │                      │
                └──────────────────────┴──────────────────────┘
                          OpenFeign REST Calls

       ┌────────────────────────┐   ┌────────────────────────┐
       │  Eureka Service        │   │  Zipkin Tracing Server  │
       │  Registry :8761        │   │       :9411             │
       └────────────────────────┘   └────────────────────────┘

       ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐
       │   employee_db_1 │ │  department_db  │ │ organization_db_1│
       │     MySQL       │ │     MySQL       │ │      MySQL      │
       └─────────────────┘ └─────────────────┘ └─────────────────┘
```

## 🚀 Key Features

* **Centralized Configuration Management** — Externalized application configurations using Spring Cloud Config Server and a remote GitHub repository.
* **Service Discovery** — Dynamic registration and discovery of microservices using Netflix Eureka.
* **API Gateway** — A unified entry point for routing client requests to internal microservices using Spring Cloud Gateway.
* **Inter-Service Communication** — Declarative REST communication between services using Spring Cloud OpenFeign.
* **Fault Tolerance** — Resilience4j Circuit Breaker and Retry mechanisms to handle downstream service failures.
* **Fallback Mechanisms** — Graceful fallback responses when dependent services are unavailable.
* **Distributed Tracing** — Request tracing across multiple services using Micrometer Tracing, Brave, and Zipkin.
* **Database per Service** — Separate MySQL databases for employee, department, and organization data.
* **RESTful APIs** — Endpoints for managing employee, department, and organization information.
* **Application Monitoring** — Spring Boot Actuator endpoints for health checks and operational monitoring.
* **Lombok Integration** — Reduced boilerplate code for Java entities, DTOs, and other application classes.

## 🛠️ Tech Stack

| Technology             | Purpose                                    |
| ---------------------- | ------------------------------------------ |
| Java 21                | Programming language                       |
| Spring Boot 3.3.4      | Microservice application framework         |
| Spring Cloud 2023.0.3  | Distributed system infrastructure          |
| Spring Cloud Gateway   | API gateway and request routing            |
| Spring Cloud Config    | Centralized configuration                  |
| Netflix Eureka         | Service discovery and registration         |
| Spring Cloud OpenFeign | Inter-service REST communication           |
| Resilience4j           | Circuit breaker and retry mechanisms       |
| Spring Data JPA        | Database persistence                       |
| Hibernate              | ORM implementation                         |
| MySQL                  | Relational database                        |
| Spring Boot Actuator   | Health monitoring and metrics              |
| Micrometer Tracing     | Distributed request tracing                |
| Brave                  | Trace propagation integration              |
| Zipkin 3.4.4           | Distributed tracing visualization          |
| Maven                  | Dependency management and build automation |
| Lombok                 | Boilerplate code reduction                 |
| Postman                | REST API testing                           |

## 📁 Project Structure

```text
Employee-Management-Microservices/
│
├── api-gateway/
│   └── API Gateway and routing configuration
│
├── config-server/
│   └── Centralized external configuration
│
├── department-service/
│   └── Department management REST APIs
│
├── employee-service/
│   └── Employee management, OpenFeign clients,
│       aggregation, and Resilience4j
│
├── organization-service/
│   └── Organization management REST APIs
│
├── service-registry/
│   └── Netflix Eureka server
│
├── zipkin-server-3.4.4-exec.jar
│   └── Standalone Zipkin tracing server
│
├── .gitignore
└── README.md
```

## ⚙️ Service Ports and Endpoints

| Service              |   Port | Description                        | Endpoint                                       |
| -------------------- | -----: | ---------------------------------- | ---------------------------------------------- |
| Config Server        | `8888` | Centralized configuration          | `http://localhost:8888/{service-name}/default` |
| Eureka Registry      | `8761` | Service discovery dashboard        | `http://localhost:8761`                        |
| API Gateway          | `9191` | API routing and gateway monitoring | `http://localhost:9191/actuator`               |
| Employee Service     | `8084` | Employee CRUD and data aggregation | `http://localhost:8084/actuator/health`        |
| Department Service   | `8083` | Department management              | `http://localhost:8083/actuator`               |
| Organization Service | `8086` | Organization management            | `http://localhost:8086/actuator`               |
| Zipkin Server        | `9411` | Distributed tracing dashboard      | `http://localhost:9411/zipkin/`                |

## 🗄️ Database Configuration

The application follows the **database-per-service** pattern, keeping each microservice's data logically isolated.

| Microservice         | Database            |
| -------------------- | ------------------- |
| Employee Service     | `employee_db_1`     |
| Department Service   | `department_db`     |
| Organization Service | `organization_db_1` |

Configure the database connection details in the appropriate application configuration files or the remote configuration repository.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db_1
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

> **Security:** Configure database credentials through environment variables or a secure secrets manager. Never commit real passwords, access tokens, or other secrets to GitHub.

## 📋 Prerequisites

Before running the project, ensure you have installed:

* Java Development Kit (JDK) 21
* Apache Maven
* MySQL Server
* Git
* Postman (for API testing)
* A remote Git repository containing the externalized Spring Cloud Config files

Verify the Java and Maven installations:

```bash
java -version
mvn -version
```

## ▶️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/employee-management-microservices.git
cd employee-management-microservices
```

Replace `YOUR_USERNAME` with your GitHub username and adjust the repository URL if needed.

### 2. Configure MySQL

Create the required databases:

```sql
CREATE DATABASE employee_db_1;
CREATE DATABASE department_db;
CREATE DATABASE organization_db_1;
```

Update the relevant database connection settings in your application configurations.

### 3. Configure the Remote Configuration Repository

Ensure that your remote Git repository contains the configuration files required by each microservice.

Example configuration filenames:

```text
employee-service.yml
department-service.yml
organization-service.yml
api-gateway.yml
service-registry.yml
```

The exact filenames and configuration profiles must match your Config Server setup.

### 4. Start the Zipkin Server

From the project root, execute:

```bash
java -jar zipkin-server-3.4.4-exec.jar
```

Open the Zipkin dashboard:

`http://localhost:9411/zipkin/`

### 5. Start the Config Server

Open the `config-server` project in your IDE and run its Spring Boot application.

Verify the server using:

`http://localhost:8888/employee-service/default`

### 6. Start the Eureka Service Registry

Run the `service-registry` application.

Open the Eureka dashboard:

`http://localhost:8761`

### 7. Start the Core Microservices

Start the following services:

1. `department-service` — Port `8083`
2. `organization-service` — Port `8086`
3. `employee-service` — Port `8084`

Confirm that the services successfully connect to MySQL, load their configurations, and register with Eureka.

### 8. Start the API Gateway

Run the `api-gateway` application on port `9191`.

Client requests can now be routed through the API Gateway according to the configured gateway routes.

> **Note:** The startup order helps reduce connection and discovery delays. If your services use Config Server imports, ensure the Config Server is available before starting dependent applications.

## 📡 API Usage

You can test the REST APIs using Postman.

### 1. Fetch Aggregated Employee Details

Retrieve an employee together with the associated department and organization information.

**Through the API Gateway**

```http
GET http://localhost:9191/employee-service/api/employees/10
```

**Direct service request**

```http
GET http://localhost:8084/api/employees/10
```

The gateway URL assumes that your gateway is configured to route `/employee-service/**` to the employee service and that the configured filters preserve or rewrite the path appropriately.

### Sample JSON Response

```json
{
  "employeeDto": {
    "id": 10,
    "firstname": "Lakkana",
    "lastname": "Dulshan",
    "email": "lakkana.dulshan@example.com",
    "departmentCode": "HR001",
    "organizationCode": "ORG_001"
  },
  "departmentDto": {
    "id": 1,
    "departmentName": "Human Resources",
    "departmentDescription": "Handles recruitment, employee relations, and payroll",
    "departmentCode": "HR001"
  },
  "organizationDto": {
    "id": 1,
    "organizationName": "ABC Technologies",
    "organizationDescription": "Software Development and IT Solutions",
    "organizationCode": "ORG_001",
    "createdDate": "2026-10-02T12:16:23.88906"
  }
}
```

*The response above is an illustrative example. Actual values depend on your database records and DTO implementation.*

### 2. Service Endpoints

Use the following base URLs when testing your services directly:

| Service              | Base URL                |
| -------------------- | ----------------------- |
| Employee Service     | `http://localhost:8084` |
| Department Service   | `http://localhost:8083` |
| Organization Service | `http://localhost:8086` |
| API Gateway          | `http://localhost:9191` |

The exact CRUD endpoint paths depend on your controller mappings.

## 🛡️ Fault Tolerance and Circuit Breaker Testing

The employee service uses Resilience4j to help handle failures when communicating with downstream services.

### Test Procedure

1. Start all microservices.
2. Send a request to retrieve an employee and its associated details.
3. Stop either the department service or the organization service.
4. Send the same request again.
5. Inspect the returned response and application logs to verify the configured fallback behavior.
6. Restart the stopped service and verify recovery.

Example request:

```http
GET http://localhost:8084/api/employees/10
```

Depending on the fallback implementation, the response may contain default department information or a `null` organization/department DTO.

### Monitoring Endpoints

**Employee service health**

`http://localhost:8084/actuator/health`

**Circuit breaker events**

`http://localhost:8084/actuator/circuitbreakerevents`

> The circuit breaker events URL is available only if the corresponding Actuator endpoint is implemented or exposed in your configuration. Configure and verify the relevant Resilience4j Actuator integration before relying on it.

## 🔍 Distributed Tracing with Zipkin

Micrometer Tracing and Brave enable distributed trace propagation across instrumented service calls. Zipkin provides a visual representation of trace spans and request latency.

### Verification Steps

1. Start the Zipkin server and all microservices.

2. Send a request through the API Gateway or directly to the employee service.

3. Open the Zipkin dashboard:

   `http://localhost:9411/zipkin/`

4. Select the relevant service, such as `employee-service`.

5. Run a trace query.

6. Open a trace to inspect the spans generated by the request.

A request that triggers Feign calls can produce a trace spanning:

```text
API Gateway
    |
    v
Employee Service
    |
    v
Department Service
    |
    v
Organization Service
```

The actual trace includes only services and calls that participate in that request. Ensure tracing instrumentation, sampling, propagation, and Zipkin reporting are configured correctly.

## 📊 Monitoring and Observability

Spring Boot Actuator provides operational endpoints for monitoring application health and metrics.

Examples:

```text
http://localhost:8084/actuator/health
http://localhost:8084/actuator/metrics
http://localhost:8084/actuator
```

Expose only the endpoints required by your application and protect sensitive monitoring endpoints in production.

## 🧠 Architecture Concepts Demonstrated

* Microservices architecture
* Service registration and discovery
* Centralized external configuration
* API Gateway pattern
* Synchronous inter-service communication
* Database-per-service pattern
* Circuit breaker and retry patterns
* Fallback handling
* Distributed tracing and observability
* RESTful API development
* Application health monitoring

## 🔮 Future Enhancements

* Add Docker and Docker Compose for containerized deployment.
* Introduce centralized logging using an appropriate logging stack.
* Add authentication and authorization with Spring Security and OAuth 2.0 or JWT.
* Implement automated tests using JUnit 5, Mockito, and Spring Boot Test.
* Add CI/CD pipelines using GitHub Actions.
* Introduce API documentation using OpenAPI and Swagger UI.
* Add metrics visualization with Prometheus and Grafana.
* Implement asynchronous messaging using RabbitMQ or Apache Kafka where appropriate.
* Deploy the services to a cloud environment.

## 👨‍💻 Author

**Lakkana Dulshan**

* GitHub: [YOUR_GITHUB_PROFILE](https://github.com/lakkanadulshan)

Replace the placeholder profile links with your actual URLs.

## 📄 License

This project is intended for educational and portfolio purposes. Add a `LICENSE` file to the repository if you want to distribute it under a specific open-source license.
