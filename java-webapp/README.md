# Java Web Application

A simple Spring Boot web application containerized with Docker.

## Features
- Home page with server time display
- Simple message board (in-memory)
- REST API endpoint at `/api/status`

## Requirements
- Docker & Docker Compose

## Run with Docker

```bash
# Build and run
docker-compose up --build

# Or manually
docker build -t java-webapp .
docker run -p 8080:8080 java-webapp
```

Then open: http://localhost:8080

## Project Structure
```
java-webapp/
├── src/main/java/com/example/app/
│   ├── Application.java       # Entry point
│   └── HomeController.java    # Web controller
├── src/main/resources/
│   ├── templates/index.html   # Thymeleaf template
│   ├── static/css/style.css   # Styles
│   └── application.properties
├── pom.xml                    # Maven build config
├── Dockerfile                 # Multi-stage Docker build
└── docker-compose.yml
```
