
---

# 🏘️ Community Management System API

> ⚠️ **This API is currently under active development.**

A scalable, production-ready **Java Spring Boot API** for managing users, communities, posts, and comments — built with clean architecture and ready for cloud deployment.

---

## ✅ Features

* 🔐 **JWT-based Authentication**
* 🧑‍🤝‍🧑 Community creation & management
* 📝 Create and manage posts & comments
* ☁️ **Google Cloud deployment-ready**
* 🧱 Clean, modular architecture (`Controller → Service → Repository`)
* 🔄 DTOs with validation and mapping via **MapStruct**
* 📊 Monitoring & health checks using **Spring Boot Actuator**

---

## 🚀 Tech Stack

| Technology               | Purpose                        |
| ------------------------ | ------------------------------ |
| **Spring Boot**          | Core framework                 |
| **Spring Web**           | REST API support               |
| **Spring Data JPA**      | Database interactions          |
| **Spring Security**      | Authentication & authorization |
| **Hibernate Validator**  | Request validation             |
| **Lombok**               | Boilerplate code reduction     |
| **MapStruct**            | DTO ↔ Entity mapping           |
| **MySQL / PostgreSQL**   | Relational database support    |
| **Spring Boot Actuator** | Monitoring & health endpoints  |
| **Spring Cloud** (opt.)  | Config & service discovery     |
| **Google Cloud**         | Cloud deployment               |

---

## 🗂️ Project Structure

```
src/
└── main/
    ├── java/
    │   └── com/project/community/
    │       ├── controller/
    │       ├── dto/
    │       ├── model/
    │       │   └── entity/
    │       ├── repository/
    │       ├── service/
    │       ├── security/
    │       ├── config/
    │       ├── exception/
    │       └── mapper/
    └── resources/
        └── application.properties
```

> 🔒 `application.properties` is **excluded from version control** for security reasons (credentials & secrets).

---

## 🧱 Core Entities

### 🧍 `User`

| Field      | Type   | Constraints           |
| ---------- | ------ | --------------------- |
| `id`       | Long   | Primary Key           |
| `fullName` | String | Unique, not blank     |
| `email`    | String | Unique, valid email   |
| `password` | String | Encrypted with BCrypt |
| `role`     | Enum   | `ADMIN`, `MEMBER`     |

---

### 🛡️ `Role` Enum

```java
public enum Role {
    ADMIN,
    MEMBER
}
```

---

## 🧾 DTOs & Validation

### 🔹 `RegisterRequestDTO`

| Field    | Type   | Validation            |
| -------- | ------ | --------------------- |
| fullName | String | `@NotBlank`           |
| email    | String | `@Email`, `@NotBlank` |
| password | String | `@Size(min = 8)`      |

### 🔹 `RegisterResponseDTO`

| Field    | Type   |
| -------- | ------ |
| id       | Long   |
| fullName | String |
| email    | String |

---

## 🔐 Security

* ✅ JWT-based stateless authentication
* 🔐 Role-based access control (`ADMIN`, `MEMBER`)
* 🔒 Passwords are securely hashed using **BCrypt**
* 🚫 No session state stored on the server

---

## 🌐 API Endpoints

### 🛂 **AuthController**

| Method | Endpoint             | Description         |
| ------ | -------------------- | ------------------- |
| POST   | `/api/auth/register` | Register a new user |
| POST   | `/api/auth/login`    | Authenticate a user |

---

### 👤 **UserController**

| Method | Endpoint                 | Description       |
| ------ | ------------------------ | ----------------- |
| GET    | `/api/users/{id}`        | Get user by ID    |
| PUT    | `/api/users/update/{id}` | Update user by ID |

---

### 🧑‍💼 **ProfileController**

| Method | Endpoint                         | Description                  |
| ------ | -------------------------------- | ---------------------------- |
| POST   | `/api/profile/create`            | Create user profile          |
| GET    | `/api/profile/{username}`        | Get user profile by username |
| PUT    | `/api/profile/update/{username}` | Update profile by username   |

> **Coming soon:**
>
> * `CommunityController`
> * `PostController`
> * `CommentController`

---

## ⚙️ Example `application.properties`

> ℹ️ This file is excluded from Git. Below is a **sample** configuration.

```properties
# App Info
spring.application.name=CommunityManagementSystemAPI
spring.profiles.active=mysql

# DB Config (Google Cloud SQL)
spring.cloud.gcp.sql.database-name=COMMUNITY
spring.cloud.gcp.sql.instance-connection-name=my-instance
spring.datasource.username=myusername
spring.datasource.password=mypassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Security Credentials
spring.security.user.name=boot
spring.security.user.password=spring

# JWT Configuration
jwt.secret=901847532619803764821593740128567923487
jwt.expiration=360000000
```

---

## ☁️ Google Cloud Deployment

* ✅ Dockerized Spring Boot application
* ✅ Connects to **Cloud SQL (MySQL/PostgreSQL)**
* ⛔ Optional: Spring Cloud Config Server
* 🚀 Supports deployment to:

  * **Cloud Run**
  * **App Engine**
  * **Compute Engine**

---