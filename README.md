# 📘 Student REST API (Spring Boot)

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![Status](https://img.shields.io/badge/Status-Active-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

A simple REST API built using **Spring Boot** to manage student data.  
This project uses **in-memory storage (no database)** and demonstrates backend fundamentals.

---

## 🚀 Features

- ✅ Get all students  
- ✅ Get student by ID  
- ✅ Add a new student  
- ✅ Update student  
- ✅ Delete student  

---

---

## 🏗️ Project Structure
```com.My_Rest_project.RestApi
│
├── model
│ └── Student.java
│
├── Repository
│ └── StudentRepo.java
│
├── Service
│ └── StudentService.java
│
├── controller
│ └── StudentController.java
│
└── RestApiApplication.java
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
🌐 API Endpoints
Method	Endpoint	Description
| Method | Endpoint         | Description       |
| ------ | ---------------- | ----------------- |
| GET    | `/students`      | Get all students  |
| GET    | `/students/{id}` | Get student by ID |
| POST   | `/students`      | Add new student   |
| PUT    | `/students`      | Update student    |
| DELETE | `/students/{id}` | Delete student    |



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

