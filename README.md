# Isura Fashion — Full Stack Ecommerce

A professional fashion ecommerce platform built from the Isura Fashion SRS/UAT baseline.

## Stack
- Frontend: React + TypeScript + Vite
- Backend: Spring Boot 3 + Java 17
- Database: PostgreSQL 16
- Auth: JWT + BCrypt
- Payments: controllable MTN MoMo / Card adapter stub
- DevOps: Docker Compose, Kubernetes manifests, GitHub Actions

## Features
- Guest catalogue browsing, search and product details
- Customer registration/login, failed-login lockout and logout
- Cart with stock validation and guest-cart persistence
- `ISURA2026` discount, quantity discount and shipping rules
- Wishlist with persistence and move-to-cart
- Checkout with MTN MoMo/Card simulation: success, decline, timeout
- Idempotent order placement and order lifecycle
- Customer order history and ownership checks
- Admin catalogue/stock management and order fulfillment
- Structured JSON-friendly API logging and health endpoint
- Responsive desktop/mobile UI

## Run with Docker
```bash
docker compose up --build
```
Frontend: http://localhost:5173
Backend: http://localhost:8081

## Run locally
Backend requires PostgreSQL. Copy environment values from `backend/.env.example`.

```bash
cd backend
mvn spring-boot:run
```

```bash
cd frontend
npm install
npm run dev
```

## Demo accounts
- Admin: `admin@isurafashion.rw` / `Admin1234`
- Customer: `demo@isurafashion.rw` / `Customer1234`

These are development seed accounts only.

## Payment simulation
On checkout choose MoMo or Card and select the desired adapter result. The production contract is represented by the backend adapter; no PAN/CVV is persisted.

## Scope note
Native mobile apps, live gateway internals, multi-vendor marketplace, physical WMS/ERP and live customer/payment data remain outside the academic scope.
