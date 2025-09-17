# 🎓 JC-Student API

A RESTful API built with Spring Boot for managing students, courses, assignments, and projects. It provides features to evaluate students with qualitative grading, generate PDF reports, and send them via email. The API is fully documented with Swagger/OpenAPI.

---

## ✨ Features
- **Student & Course Management**: Full CRUD operations for students and courses.
- **Assignments & Projects**: Create, evaluate, and manage academic assignments and projects.
- **Qualitative Evaluation**: Grade students with predefined levels (`INSUFFICIENT`, `GOOD`, `EXCELLENT`, etc.).
- **Detailed Project Evaluation**: Assess projects based on multiple criteria (CRUD, security, testing, deploy, documentation).
- **PDF Report Generation**: Export evaluations and grades into clean PDF reports.
- **Email Integration**: Automatically send reports to students or groups via email.
- **Authentication & Security**: User management with Spring Security.
- **API Documentation**: Interactive Swagger UI available out-of-the-box.

---

## 🛠️ Tech Stack
- **Spring Boot 2.7**: Backend framework for building the API.
- **Spring Data JPA (Hibernate)**: Data persistence layer with MySQL.
- **Spring Security**: Authentication and authorization for user management.
- **Spring Validation**: Ensures robust request validation.
- **iText PDF**: Generates PDF reports for evaluations.
- **Spring Mail**: Sends reports directly to email.
- **Lombok**: Reduces boilerplate code.
- **Swagger / Springdoc OpenAPI**: Provides live documentation and testing of the API.

---

## 📂 Project Structure
```
src/
├── main/
│   ├── java/com/jcaro/jcstudentapi/
│   │   ├── application/  # DTOs, exceptions, mappers, use cases
│   │   ├── domain/       # Domain models, repositories
│   │   ├── infrastructure/ # Configs, controllers, persistence, security
│   │   └── JcStudentApiApplication.java
│   └── resources/
│       ├── application.properties  # Configuration (DB, mail, Swagger)
│       └── templates/               # Email templates
└── test/  # Unit and integration tests
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17  
- Maven 3.9+  
- MySQL 8+

### Installation

Clone the repository:
```bash
git clone https://github.com/your-username/jc-student-api.git
cd jc-student-api
```

Build and run the project:
```bash
mvn clean install
mvn spring-boot:run
```

### ⚙️ Configuration

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jcstudentdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Mail settings
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-password

# Swagger
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```

### 🔑 Authentication

The API uses JWT authentication:

- Login via `/api/login` with valid credentials.
- Receive `accessToken` and `expiresIn`.
- Include the token in requests:
```
Authorization: Bearer <token>
```

### 📌 API Endpoints (Main)

#### 👤 Users
- `POST /api/users` → Create a new user  
- `GET /api/users/{id}` → Get user by ID  
- `PUT /api/users/{id}` → Update user  

#### 🎓 Students
- `POST /api/students` → Create student  
- `GET /api/students` → List all students  
- `GET /api/students/{id}` → Get student by ID  
- `PUT /api/students/{id}` → Update student  
- `DELETE /api/students/{id}` → Delete student  

#### 📚 Courses
- `POST /api/courses` → Create course  
- `GET /api/courses` → List all courses  
- `GET /api/courses/{id}` → Get course by ID  
- `PUT /api/courses/{id}` → Update course  
- `DELETE /api/courses/{id}` → Delete course  

#### 📝 Assignments
- `POST /api/assignments` → Create assignment  
- `GET /api/assignments` → List all assignments  
- `GET /api/assignments/{id}` → Get assignment by ID  
- `PUT /api/assignments/{id}` → Update assignment  
- `DELETE /api/assignments/{id}` → Delete assignment  

#### 📂 Projects
- `POST /api/projects` → Create project  
- `GET /api/projects` → List all projects  
- `GET /api/projects/{id}` → Get project by ID  
- `PUT /api/projects/{id}` → Update project  
- `DELETE /api/projects/{id}` → Delete project  

---

## 📄 License

This project currently has no license. All rights are reserved by the author.
