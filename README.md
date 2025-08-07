
---

# 🏘️ Community Management System API

A scalable, production-ready **Java Spring Boot API** for managing users, communities, posts, and comments — built with clean architecture and deployed on **Google Cloud Run**.

> ✅ **Live API:**
> This API is currently deployed to **Google Cloud Run** and will be available for the next 4–5 days:
> 🔗 [Live API Endpoint](https://community-system-753662617279.us-central1.run.app)

> 📬 **Postman Collection:**
> To test the API (authentication, profile, community, post, and comment operations), use the Postman collection below.
> You will need a **JWT token** after logging in to access protected routes.
> 🔗 [Community Management System — Postman Collection](https://www.postman.com/flight-technologist-23919603/the-public-api-s/collection/pgxgqsj/community-mangement-system?action=share&creator=42910295)

---

## ✅ Features

* 🔐 **JWT-based Authentication**
* 🧑‍🤝‍🧑 Community creation & management
* 📝 Support for posts and comments
* ☁️ **Google Cloud deployment-ready**
* 🧱 Clean, modular architecture (`Controller → Service → Repository`)
* 🔄 DTOs with validation and mapping via **MapStruct**
* 📊 Monitoring & health checks with **Spring Boot Actuator**

---

## 🚀 Tech Stack

| Technology               | Purpose                        |
| ------------------------ | ------------------------------ |
| **Spring Boot**          | Core framework                 |
| **Spring Web**           | REST API support               |
| **Spring Data JPA**      | Database interactions          |
| **Spring Security**      | Authentication & authorization |
| **Hibernate Validator**  | Input validation               |
| **Lombok**               | Boilerplate reduction          |
| **MapStruct**            | DTO ↔ Entity mapping           |
| **MySQL / PostgreSQL**   | Relational database            |
| **Spring Boot Actuator** | Monitoring & metrics           |
| **Spring Cloud** (opt.)  | Config & service discovery     |
| **Google Cloud**         | Deployment platform            |

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

> 🔒 `application.properties` is excluded from version control for security reasons (credentials & secrets).

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
* 🔒 Passwords hashed using **BCrypt**
* 🚫 No server-side session storage

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

| Method | Endpoint                         | Description                |
| ------ | -------------------------------- | -------------------------- |
| POST   | `/api/profile/create`            | Create a user profile      |
| GET    | `/api/profile/{username}`        | Get profile by username    |
| PUT    | `/api/profile/update/{username}` | Update profile by username |

---

### 🏘️ **CommunityController**

| Method | Endpoint                                | Description              |
| ------ | --------------------------------------- | ------------------------ |
| POST   | `/api/community/create`                 | Create a new community   |
| GET    | `/api/community/{searchByName}`         | Get community by name    |
| PUT    | `/api/community/update/{communityName}` | Update community by name |

---

### 📝 **PostController**

| Method | Endpoint                                    | Description                  |
| ------ | ------------------------------------------- | ---------------------------- |
| POST   | `/api/community/{communityId}/posts/create` | Create a post in a community |
| GET    | `/api/community/posts/{communityId}`        | Get all posts in a community |

---

### 💬 **CommentController**

| Method | Endpoint                           | Description                      |
| ------ | ---------------------------------- | -------------------------------- |
| POST   | `/api/community/{postId}/comments` | Add a comment to a specific post |

---

## ⚙️ Example `application.properties`

> ℹ️ This file is excluded from version control. Below is a **sample configuration**:

```properties
# App Info
spring.application.name=CommunityManagementSystemAPI
spring.profiles.active=mysql

# Database Config (Google Cloud SQL)
spring.cloud.gcp.sql.database-name=COMMUNITY
spring.cloud.gcp.sql.instance-connection-name=my-instance
spring.datasource.username=myusername
spring.datasource.password=mypassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Spring Security (default credentials)
spring.security.user.name=boot
spring.security.user.password=spring

# JWT Configuration
jwt.secret=901847532619803764821593740128567923487
jwt.expiration=360000000
```

---

## ☁️ Google Cloud Deployment

* ✅ Dockerized Spring Boot application
* ✅ Connected to **Cloud SQL (MySQL)**
* ✅ Deployed via **Google Cloud Run**

---

Let me know if you'd like this exported to a `.md` file or integrated into your GitHub repository.
