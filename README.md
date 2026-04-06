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

## Running Locally

### Prerequisites

- Java 21 or newer
- A local MySQL instance
- A database named `full-stack-ecommerce`

### Configuration

The app reads database settings from environment variables when present and falls back to local defaults:

- `APP_DB_URL`
- `APP_DB_USERNAME`
- `APP_DB_PASSWORD`

Default local values are defined in `src/main/resources/application.properties`.

### Start the Application

```powershell
.\mvnw.cmd spring-boot:run
```

The API base path is `/api`.

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
- `BootstrapData` seeds a small set of customers on startup when the backing database already contains division records.

## Why This Repo Exists

This project was originally built as coursework and then lightly polished for portfolio review. The goal of this public version is to show backend fundamentals clearly: data modeling, repository design, validation, and transactional persistence.
