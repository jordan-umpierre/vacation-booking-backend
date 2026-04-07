# Travel Booking Backend

Spring Boot REST backend for a travel booking checkout workflow. The API exposes travel catalog data through Spring Data REST and provides a custom transactional checkout endpoint for submitting cart purchases.

## What It Demonstrates

- JPA entity modeling for customers, carts, cart items, vacations, excursions, divisions, and countries
- Spring Data REST repositories for catalog-style API endpoints
- Custom REST controller for checkout submission
- Transactional service logic that persists carts, cart items, customer relationships, and order tracking numbers
- Jakarta validation on purchase payloads
- MySQL-backed local development with Docker Compose
- H2-backed test profile for lightweight Spring context verification
- Seed data bootstrapping for demo geography, travel catalog data, and sample customers

## Tech Stack

- Java 21
- Spring Boot 4.0.0
- Spring Data JPA
- Spring Data REST
- MySQL 8.4
- H2 for tests
- Maven Wrapper
- Docker Compose

## Run With Docker

Recommended for the quickest first run because it starts both MySQL and the API.

From the repository root:

```bash
docker compose up --build
```

This starts:

- MySQL on `localhost:3306`
- Spring Boot API on `localhost:8080`

The Docker setup uses the `docker` Spring profile, enables Hibernate schema updates for the MySQL database, and seeds a small demo catalog on first startup.

Open the API entry point:

```text
http://localhost:8080/api
```

Stop the stack with:

```bash
docker compose down
```

## Run Locally

Prerequisites:

- Java 21+
- A local MySQL instance
- A database named `full-stack-ecommerce`

The app reads database settings from environment variables when present and falls back to local defaults in `src/main/resources/application.properties`:

- `APP_DB_URL`
- `APP_DB_USERNAME`
- `APP_DB_PASSWORD`

The default local profile uses `spring.jpa.hibernate.ddl-auto=none`, so it expects an existing schema. If you want the app to initialize against an empty database, use the Docker profile.

Run from the repository root:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

To reuse only the Dockerized MySQL container while running the app from your IDE or terminal:

```bash
docker compose up -d db
./mvnw spring-boot:run "-Dspring-boot.run.profiles=docker"
```

This repository is backend-only. It is normal for `http://localhost:8080/` to return `404`; the API is exposed under `/api`.

## Test And Verify

Run tests from the repository root:

```bash
./mvnw test
```

On Windows PowerShell:

```powershell
.\mvnw.cmd test
```

Tests use an in-memory H2 database so the Spring context can load without a local MySQL dependency.

Manual smoke test checklist:

- Start the Docker stack and open `http://localhost:8080/api`.
- Confirm catalog endpoints such as `/api/vacations` and `/api/excursions` return data.
- Submit a checkout request to `/api/checkout/purchase` and confirm the response includes an order tracking number.

## Key Endpoints

- `GET /api/vacations`
- `GET /api/excursions`
- `GET /api/countries`
- `GET /api/divisions`
- `POST /api/checkout/purchase`

Repository CORS is configured for a local Angular frontend at `http://localhost:4200`, but this repo intentionally contains the backend API only.

## Resume Bullet

Built a Spring Boot travel booking backend with Spring Data REST, JPA entity relationships, MySQL persistence, Docker Compose local infrastructure, validation, seed data bootstrapping, and a transactional checkout service that returns generated order tracking numbers.
