# Travel Booking Backend

Spring Boot REST backend for a travel booking checkout workflow. This project exposes travel product data through Spring Data REST and provides a custom checkout endpoint for submitting a cart purchase.

## What It Demonstrates

- JPA entity modeling for customers, carts, cart items, vacations, excursions, divisions, and countries
- Spring Data REST repositories for catalog-style endpoints
- A transactional checkout service that persists cart and cart item relationships
- Validation on purchase payloads and seed data bootstrapping for local development

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA
- Spring Data REST
- MySQL for local development
- H2 for test-time context loading
- Maven Wrapper

## Docker

Recommended for the quickest first run.

You can run the backend and MySQL together with Docker Compose:

```powershell
docker compose up --build
```

This starts:

- a MySQL container on `localhost:3306`
- the Spring Boot API on `localhost:8080`

The Docker setup uses the `docker` Spring profile, which enables Hibernate schema creation for an empty MySQL database and seeds a small demo catalog plus sample customers on first startup.

To stop the stack:

```powershell
docker compose down
```

## Running Locally

### Prerequisites

- Java 21 or newer
- A local MySQL instance
- A database named `full-stack-ecommerce`

For most users, Docker is the easiest way to start the project. The direct Maven run below is best if you already have MySQL available locally.

### Configuration

The app reads database settings from environment variables when present and falls back to local defaults:

- `APP_DB_URL`
- `APP_DB_USERNAME`
- `APP_DB_PASSWORD`

Default local values are defined in `src/main/resources/application.properties`.

The default local profile uses `spring.jpa.hibernate.ddl-auto=none`, so it expects an existing schema in MySQL. If you want the application to initialize against an empty MySQL database, use the Docker profile instead.

### Start the Application

```powershell
.\mvnw.cmd spring-boot:run
```

The API base path is `/api`.

If you want to reuse the Dockerized MySQL instance while running the app from your IDE or terminal, start only the database container first:

```powershell
docker compose up -d db
```

Then run the app with the Docker profile:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=docker"
```

## Testing

```powershell
.\mvnw.cmd test
```

Tests use an in-memory H2 database so the Spring context can load without requiring your local MySQL instance.

## Key Endpoints

- `GET /api/vacations`
- `GET /api/excursions`
- `GET /api/countries`
- `GET /api/divisions`
- `POST /api/checkout/purchase`

## Notes

- Repository CORS is configured for a local Angular frontend at `http://localhost:4200`.
- This repository contains the backend API only; the original frontend is expected to run separately on `http://localhost:4200`.
- `BootstrapData` seeds demo geography, travel catalog data, and sample customers for local development.

## Why This Repo Exists

This project was originally built as coursework and then lightly polished for portfolio review. The goal of this public version is to show backend fundamentals clearly: data modeling, repository design, validation, and transactional persistence.
