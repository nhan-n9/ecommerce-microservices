# Ecommerce Microservices Platform

This project is an end-to-end ecommerce backend platform built with Spring Boot microservices.
It focuses on a production-style workflow that covers development, containerization, CI/CD automation,
Kubernetes deployment, and cloud infrastructure provisioning.


## CI/CD Workflow

Below is the target CI/CD workflow and tools used in this project:

![CI/CD Flow](cicd-flow.png)

Typical flow includes:

1. Build and test services
2. Run code quality checks (SonarQube)
3. Scan container images for vulnerabilities (Trivy)
4. Push versioned artifacts/images
5. Deploy through ArgoCD (GitOps)
6. Observe runtime metrics with Prometheus

## AWS Infrastructure Design

Below is the AWS architecture used for deployment.
The design follows a cost-limited strategy suitable for learning and free-tier usage.

![AWS Infrastructure](aws-infras.png)

Infrastructure and deployment assets in this repo:

- `helm/`: Kubernetes packaging and service templates
- `docker-compose.yml`: local development dependencies

## Repository Structure

```text
services/
	config-server/
	eureka-server/
	gateway-service/
	identity-service/
	kafka-service/
	noti-service/
	order-service/
	payment-service/
	product-service/
	user-service/
helm/

docker-compose.yml
```

## Quick Start (Local)

### 1. Prerequisites

- Java 21
- Maven 3.9+
- Docker + Docker Compose

### 2. Start local dependencies

```bash
docker compose up -d
```

### 3. Run services

From each service directory under `services/`:

```bash
./mvnw spring-boot:run
```

Recommended startup order:

1. `config-server`
2. `eureka-server`
3. `gateway-service`
4. domain services (`identity-service`, `user-service`, `product-service`, etc.)

## Deployment Strategy

This project follows an ArgoCD service-oriented strategy (one application per service),
making rollback and independent service delivery easier as the platform grows.

## Project Highlights

Add your screenshots in this section to showcase operational visibility and delivery quality.

### ArgoCD deployment handling



### Prometheus monitoring



### SonarQube code scan



### Trivy image scan

