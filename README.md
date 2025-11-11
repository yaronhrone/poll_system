Poll Application

A full-stack poll platform that allows users to register, log in, and complete polls.
After submission, poll results are displayed in an interactive chart.
The backend is built using Spring Boot Microservices (Java), and the frontend uses React with TypeScript.


---

Features

User Authentication – register and log in securely.

Dynamic Polls – users can answer various poll questions.

Visual Results – poll results are displayed in a graph (Recharts).

Microservice Architecture – separate backend services for users and polls.

REST API Integration – smooth communication between client and backend.

H2 In-memory Database – simple setup for local development.



---

Tech Stack

Frontend:

React.js

TypeScript

Axios

React Router

React Icons

Recharts


Backend:

Java

Spring Boot

Spring Web

Spring Security

H2 Database

REST API

Microservices Architecture



---

Project Structure

poll-app/
│
├── backend-user/               # User authentication & management service
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── backend-microservice/       # Poll and results service
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── frontend/                   # React + TypeScript client
│   ├── src/
│   ├── package.json
│   └── ...
│
└── README.md


---

Installation & Run Instructions

1. Clone the repository

git clone https://github.com/your-username/poll-app.git
cd poll-app

2. Run the backend services

User Service

cd backend-user
mvn spring-boot:run

Runs on: http://localhost:8080

Poll Service

cd backend-microservice
mvn spring-boot:run

Runs on: http://localhost:8081

> Both services use H2 Database by default (configured in application.properties).



3. Run the frontend

cd frontend
npm install
npm start

Runs on: http://localhost:3000


---

Usage

1. Open the app in your browser (http://localhost:3000).


2. Register or log in to your account.


3. Fill out the available poll.


4. View your answers displayed in a graph.
