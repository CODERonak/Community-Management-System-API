Here's the **corrected and cleaned-up version** of your Markdown:

---

```markdown
# 🏘️ Community Management System API

⚠️ *This API is currently under active development.*

A scalable, production-ready **Java Spring Boot API** for managing users, communities, posts, and comments — built with clean architecture and ready for cloud deployment.

---

## ✅ Features

- 🔐 **User Authentication** (JWT-based)
- 🧑‍🤝‍🧑 Community creation & management
- 📝 Posts and comments support
- ☁️ Deployment-ready for **Google Cloud**
- 🧱 Modular, layered architecture (Controller → Service → Repository)
- 🔄 DTOs with validation and mapping (MapStruct)
- 📊 Monitoring with Spring Boot Actuator

---

## 🚀 Tech Stack

| Technology             | Purpose                               |
|------------------------|----------------------------------------|
| Spring Boot            | Core framework                         |
| Spring Web             | REST API support                       |
| Spring Data JPA        | Database interactions                  |
| Spring Security        | Authentication & authorization         |
| Hibernate Validator    | Input validation                       |
| Lombok                 | Boilerplate code reduction             |
| MapStruct              | DTO <-> Entity mapping                 |
| MySQL / PostgreSQL     | Relational database                    |
| Spring Boot Actuator   | Monitoring & health checks             |
| Spring Cloud (optional)| Config & discovery                     |
| Google Cloud           | Cloud deployment                       |

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
├── application.properties

````

---

## 🧱 Core Entities

### ✅ `User`

| Field     | Type   | Constraints              |
|-----------|--------|--------------------------|
| id        | Long   | Primary Key              |
| fullName  | String | Unique, Not Blank        |
| email     | String | Unique, Valid Email      |
| password  | String | Encrypted with BCrypt    |
| role      | Enum   | `ADMIN`, `MEMBER`        |

### ✅ `Role` Enum

```java
public enum Role {
    ADMIN,
    MEMBER
}
````

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

## 🔐 Security (Production Grade)

* ✅ JWT-based Authentication
* 🔐 Role-based access control (`ADMIN`, `MEMBER`)
* 🔒 Password hashing via BCrypt
* ⚙️ Stateless sessions

---

## 🌐 API Endpoints

### 🛂 **AuthController**

| Method | Endpoint         | Description         |
| ------ | ---------------- | ------------------- |
| POST   | `/auth/register` | Register a new user |
| POST   | `/auth/login`    | Authenticate user   |

### 👤 **UserController**

| Method | Endpoint        | Description              |
| ------ | --------------- | ------------------------ |
| GET    | `/users/me`     | Get current user profile |
| PUT    | `/users/update` | Update user profile      |

> *Upcoming: `ProfileController`, `CommunityController`, `PostController`, `CommentController`*

---

## ☁️ Google Cloud Deployment (Planned)

* Dockerize the application
* Use Cloud SQL (MySQL or PostgreSQL)
* Optional: Spring Cloud Config Server
* Deploy via Cloud Run, App Engine, or Compute Engine

---