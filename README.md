# 📘 Student REST API (Spring Boot)

A Spring Boot & Thymeleaf-based Student Management System migrated from an in-memory storage implementation (`ArrayList`) to a persistent **PostgreSQL** relational database using **Spring Data JPA**. It features a modern layout with responsive styles and dynamic, non-reloading live search.

---

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![Status](https://img.shields.io/badge/Status-Active-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

## 🚀 Key Features

*   **Database Persistence**: Seamlessly powered by PostgreSQL for reliable storage of student records.
*   **Spring Data JPA & Hibernate**: Replaces all custom collection streams with standardized repository queries.
*   **Dynamic Live Search**: Real-time filtering by Name or Email powered by a debounced JavaScript `fetch()` request (no full-page reloads).
*   **Server-Side Rendering (Thymeleaf)**: Initial page load renders data directly from the server.
*   **Lombok Support**: Less boilerplate code in data objects.
*   **Server-side Validation**: Data validation utilizing annotations like `@NotBlank`, `@Email`, `@Min`, `@Max`, and `@Positive`.

---

## 🛠️ Tech Stack

*   **Backend**: Java 17, Spring Boot 3, Spring Data JPA, Hibernate
*   **Database**: PostgreSQL
*   **Frontend**: HTML5, Thymeleaf, Vanilla JS, Bootstrap 5, Custom CSS
*   **Build Tool**: Maven

---

## 📋 Getting Started

### 1. Prerequisites
Make sure you have the following installed on your system:
*   Java Development Kit (JDK) 17 or higher
*   Maven 3+
*   PostgreSQL running locally

### 2. Configure Database
Login to PostgreSQL and create a database named `studentdb`:
```sql
CREATE DATABASE studentdb;
---

## 📁 Project Structure

```text
Student_RestApi_project/
└── RestApi/
    ├── .mvn/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/
    │       │       └── My_Rest_project/
    │       │           └── RestApi/
    │       │               ├── model/
    │       │               │   └── Student.java
    │       │               ├── Repository/
    │       │               │   └── StudentRepo.java
    │       │               ├── Service/
    │       │               │   └── StudentService.java
    │       │               ├── RestApiApplication.java
    │       │               ├── StudentController.java
    │       │               ├── StudentViewController.java
    │       │               └── ViewController.java
    │       └── resources/
    │           ├── static/
    │           │   ├── css/
    │           │   ├── js/
    │           │   └── favicon.jpg
    │           ├── templates/
    │           │   ├── fragments/
    │           │   ├── add-student.html
    │           │   ├── edit-student.html
    │           │   ├── index.html
    │           │   └── layout.html
    │           ├── application.properties
    │           └── init.sql
    └── pom.xml
```
```
spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=YOUR_POSTGRES_USER
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```
---

## 🧠 Architecture
Controller → Service → Repository → Model


- **Controller** → Handles API requests  
- **Service** → Business logic  
- **Repository** → In-memory data handling  
- **Model** → Student structure  

---

## 📦 Student Model

```json
{
  "roll": 1,
  "name": "Tarun",
  "email": "abc@123",
  "marks": 23.2
}
```
## 🔌 API Endpoints (REST API)

| Method | Endpoint | Description |
|--------|----------|-------------|
| **GET** | `/api/students` | Retrieve all students |
| **GET** | `/api/students/{id}` | Get student details by Roll number |
| **POST** | `/api/students` | Register a new student |
| **PUT** | `/api/students` | Update existing student |
| **DELETE** | `/api/students/{id}` | Delete student |
| **GET** | `/api/students/search?query=<keyword>` | Perform case-insensitive search by **Name** or **Email** |
| **GET** | `/api/students/search?name=<name>&email=<email>&minMarks=<min>&maxMarks=<max>` | Filter students using optional combined parameters |

⚙️ How to Run
Prerequisites
Java 17+
Maven

Steps
```
git clone <your-repo-link>
```
```
cd RestApi
```
```
mvn clean install

```
```
mvn spring-boot:run
```
🔌 Server
```
http://localhost:8080
```

