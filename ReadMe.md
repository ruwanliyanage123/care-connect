High-Level Architecture Diagram

![img_1.png](high-level-arthitecture.png)

Backend Architecture Diagram

![img_2.png](backend-architecture.png)

# CareConnect Architecture Overview

CareConnect is a microservices-based healthcare management platform with:

- **Microfrontends** (React + Webpack Module Federation)
- **Backend Microservices** (Spring Boot)
- **Separate PostgreSQL databases per service**
- **API Gateway / Ingress Layer** for routing
- Room to extend to async messaging (Kafka/RabbitMQ) later.

## Frontend Microfrontends

- `care-connect-root-app`  
  Hosts layout, sidebar, topbar and routes to remotes.

- `care-connect-dashboard-app`  
  Dashboard with total patients, consultants, appointments, and revenue from `/api/v1/reports/dashboard`.

- `care-connect-patients-app`
    - CRUD for patients
    - Integrated with `patient-service` (`/api/v1/patients`)
    - Has modal for Add/Edit patient.

- `care-connect-consultants-app`
    - List + status of consultants
    - Integrated with `consultant-service` (`/api/v1/consultants`).

- `care-connect-appointments-app`
    - Shows appointments table
    - Summary: total, upcoming, cancelled
    - Create appointment modal (POST to `/api/v1/appointments`).

- `care-connect-payments-app`
    - Lists payments
    - Shows summary (paid, pending, failed)
    - Integrates with `payment-service` (`/api/v1/payments`).

## Backend Microservices

- `patient-service`
    - Manages patient entities
    - REST: `/api/v1/patients`
    - DB: `careconnect_patient_db`

- `consultant-service`
    - Manages consultants/providers
    - REST: `/api/v1/consultants`
    - DB: `careconnect_consultant_db`

- `appointment-service`
    - Schedules appointments
    - REST: `/api/v1/appointments`
    - DB: `careconnect_appointment_db`

- `payment-service`
    - Invoices and payments
    - REST: `/api/v1/payments`
    - DB: `careconnect_billing_db`

- `reports-service`
    - Aggregated metrics for dashboard
    - REST: `/api/v1/reports/dashboard`
    - Calls: `patient-service`, `consultant-service`, `appointment-service`, `payment-service`.
