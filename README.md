# 🚀 Programming Challenges Repository

**A collection of fullstack/backend challenges from real job interviews, implemented using Angular & Spring Boot.**

---

## 📌 Overview

This repository is dedicated to solving real-world backend and fullstack programming challenges to sharpen my skills and prepare for job interviews. These challenges are inspired by:

- Technical assessments from companies
- Online coding platforms
- Practical real-world scenarios

Each project simulates the kind of work you'd encounter in a production environment, with proper structure, best practices, and common features like authentication, persistence, and RESTful APIs.

---

## 🧰 Tech Stack

The core technologies used across the projects include:

- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot) (Java 17+)
- **Frontend**: [Angular](https://angular.io/) (Node.js 16+)
- **Database**: PostgreSQL, H2, or MySQL *(varies by challenge)*
- **Authentication**: JWT tokens, Spring Security
- **Tools & Utilities**:
  - Docker (where applicable)
  - Postman (for testing APIs)
  - Swagger/OpenAPI (for API documentation)
  - Flyway or Liquibase (for database migrations)
  - JPA/Hibernate (for ORM)

---


> Some challenges may be backend-only or include mock frontends, depending on the nature of the task.

---

## 🛠️ Setup Guide

### 1. Prerequisites

Make sure you have the following installed:

- Java 17+
- Node.js 16+
- Angular CLI (`npm install -g @angular/cli`)
- Docker (optional, but useful for containerized challenges)

### 2. Running a Project Locally

```bash
# Backend
cd challenge-X/backend
./mvnw spring-boot:run

# Frontend (in a separate terminal)
cd challenge-X/frontend
npm install
ng serve
```

Replace challenge-X with the specific folder name of the challenge you want to run.

