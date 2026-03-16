# Ecommerce Microservices Platform

This project is an end-to-end ecommerce backend platform built with Spring Boot microservices.
It focuses on a production-style workflow that covers development, containerization, CI/CD automation,
Kubernetes deployment, and cloud infrastructure provisioning.


## CI/CD Workflow

Below is the target CI/CD workflow and tools used in this project:

<img width="1263" height="434" alt="cicd-flow" src="https://github.com/user-attachments/assets/6adf8fe0-b979-4eea-8b85-5ca1daf80cb3" />

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

<img width="1190" height="691" alt="aws-infras" src="https://github.com/user-attachments/assets/600c4f9c-6bb1-4cfd-a130-33f963127d97" />

Infrastructure and deployment assets:

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

### ArgoCD deployment handling
<img width="1500" height="635" alt="argocd" src="https://github.com/user-attachments/assets/81447347-f44d-44e0-84d0-5fa385d40a8f" />

### Prometheus monitoring
<img width="1501" height="787" alt="promethus" src="https://github.com/user-attachments/assets/fbe4ea6e-da19-4c15-b7b1-f45a7676de0d" />

### SonarQube code scan
<img width="1496" height="403" alt="sonarqube" src="https://github.com/user-attachments/assets/6f4d6725-b9ff-412f-942a-e562ba8b2988" />


### Trivy image scan
<img width="1482" height="655" alt="trivy" src="https://github.com/user-attachments/assets/079a7c62-381b-4a0e-8ca9-54ecf9c08ff3" />

