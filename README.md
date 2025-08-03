
---

# 🏘️ Community Management System API

> ⚠️ **This API is currently under active development.**

A scalable, production-ready **Java Spring Boot API** for managing users, communities, posts, and comments — built with clean architecture and ready for cloud deployment.

---

### 🚨 Note:

* The `application.properties` file has been deleted and is ignored via `.gitignore` for security (as it may contain sensitive properties).

---

## ✅ Features

* 🔐 **User Authentication** (JWT-based)
* 🧑‍🤝‍🧑 Community creation & management
* 📝 Support for posts and comments
* ☁️ **Google Cloud deployment-ready**
* 🧱 Modular, layered architecture (`Controller → Service → Repository`)
* 🔄 DTOs with validation and mapping using **MapStruct**
* 📊 Monitoring with **Spring Boot Actuator**

---

## 🚀 Tech Stack

| Technology                  | Purpose                        |
| --------------------------- | ------------------------------ |
| **Spring Boot**             | Core framework                 |
| **Spring Web**              | REST API support               |
| **Spring Data JPA**         | Database interactions          |
| **Spring Security**         | Authentication & authorization |
| **Hibernate Validator**     | Input validation               |
| **Lombok**                  | Boilerplate code reduction     |
| **MapStruct**               | DTO <-> Entity mapping         |
| **MySQL / PostgreSQL**      | Relational database            |
| **Spring Boot Actuator**    | Monitoring & health checks     |
| **Spring Cloud (optional)** | Config & service discovery     |
| **Google Cloud**            | Cloud deployment               |

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

---

## 🧱 Core Entities

### ✅ `User`

| Field      | Type   | Constraints         |
| ---------- | ------ | ------------------- |
| `id`       | Long   | Primary Key         |
| `fullName` | String | Unique, Not Blank   |
| `email`    | String | Unique, Valid Email |
| `password` | String | Encrypted (BCrypt)  |
| `role`     | Enum   | `ADMIN`, `MEMBER`   |

---

### ✅ `Role` Enum

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

---

### 🔹 `RegisterResponseDTO`

| Field    | Type   |
| -------- | ------ |
| id       | Long   |
| fullName | String |
| email    | String |

---

## 🔐 Security

* ✅ JWT-based authentication
* 🔐 Role-based access (`ADMIN`, `MEMBER`)
* 🔒 Passwords hashed using **BCrypt**
* ⚙️ Stateless sessions

---

## 🌐 API Endpoints

### 🛂 **AuthController**

| Method | Endpoint         | Description         |
| ------ | ---------------- | ------------------- |
| POST   | `/auth/register` | Register a new user |
| POST   | `/auth/login`    | Authenticate a user |

---

### 👤 **UserController**

| Method | Endpoint        | Description              |
| ------ | --------------- | ------------------------ |
| GET    | `/users/me`     | Get current user profile |
| PUT    | `/users/update` | Update user profile      |

> **Upcoming:** `ProfileController`, `CommunityController`, `PostController`, `CommentController`

---

## ⚙️ Application Properties (Example)

> **Note:** The following values are **example placeholders** and **not actually used** in version control.

```properties
# Application Name
spring.application.name=CommunityManagementSystemAPI

# Profiles
spring.profiles.active=mysql

# Database Configuration (Google Cloud SQL)
spring.cloud.gcp.sql.database-name=COMMUNITY
spring.cloud.gcp.sql.instance-connection-name=my-instance
spring.datasource.username=myusername
spring.datasource.password=mypassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Security
spring.security.user.name=boot
spring.security.user.password=spring

# JWT
jwt.secret=901847532619803764821593740128567923487
jwt.expiration=360000000
```

---

## ☁️ Google Cloud Deployment (Planned)

* ✅ Dockerize the application
* ✅ Connect to **Cloud SQL** (MySQL/PostgreSQL)
* ⛔ Optional: Use **Spring Cloud Config Server**
* 🚀 Deploy to **Cloud Run**, **App Engine**, or **Compute Engine**

---