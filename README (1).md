# Logistics Management System

> A production-inspired microservices based Logistics & Shipment
> Management Platform built with Spring Boot.

## Overview

The Logistics Management System is a backend-focused enterprise
application that simulates how modern courier and logistics companies
manage shipments, payments, driver allocation and shipment tracking. The
project follows a microservice architecture and emphasizes scalability,
loose coupling and asynchronous communication.

### Implemented Services

-   User Service
-   Shipment Service
-   Payment Service
-   Shared Common Module

### Planned Infrastructure

-   Spring Cloud Gateway
-   Eureka Service Registry
-   Notification Service
-   Dockerized deployment of all services

------------------------------------------------------------------------

# Features

-   JWT Authentication
-   Role Based Access Control (ADMIN, USER, DRIVER)
-   Shipment Creation
-   Driver Assignment
-   Shipment Tracking
-   Shipment Status History
-   Razorpay Payment Integration
-   Kafka based asynchronous communication
-   Redis caching
-   PostgreSQL persistence
-   RESTful APIs
-   Validation using Jakarta Validation
-   Centralized DTO sharing using Common module

------------------------------------------------------------------------

# Technology Stack

  Category           Technology
  ------------------ -----------------------------
  Language           Java 17
  Framework          Spring Boot
  Security           Spring Security + JWT
  Database           PostgreSQL
  ORM                Spring Data JPA / Hibernate
  Cache              Redis
  Messaging          Apache Kafka
  Build Tool         Maven
  Payment            Razorpay Test API
  Containerization   Docker
  API Testing        Postman

------------------------------------------------------------------------

# Architecture

``` text
                Client
                   |
        Spring Cloud Gateway (Planned)
                   |
     ------------------------------------
     |          |          |            |
 User Service Shipment  Payment   Notification(planned)
                  |
             PostgreSQL

Shipment Service
        |
        | Kafka Events
        |
 Payment Service

Shipment Service
        |
      Redis
        |
 Driver Location Cache
```

------------------------------------------------------------------------

# Authentication Flow

1.  User registers.
2.  User logs in.
3.  JWT token is generated.
4.  Every secured endpoint validates JWT.
5.  Role Based Authorization controls endpoint access.

Roles:

-   ADMIN
-   USER
-   DRIVER

------------------------------------------------------------------------

# Shipment Workflow

1.  User creates shipment.
2.  Shipment stored with **PENDING_PAYMENT**.
3.  Shipment requests checkout.
4.  Payment Service generates Razorpay Order.
5.  User completes payment.
6.  Payment signature verified.
7.  Shipment updated.
8.  Driver assigned.
9.  Shipment status history maintained.
10. User tracks shipment using tracking number.

------------------------------------------------------------------------

# Payment Flow

User

↓

Shipment Service

↓

Kafka Event

↓

Payment Service

↓

Razorpay Order Creation

↓

Frontend Checkout

↓

Payment Verification

↓

Shipment Updated

The payment service never trusts the frontend. Every payment is verified
using Razorpay Signature Verification before updating shipment status.

------------------------------------------------------------------------

# Redis Strategy

Redis is used for caching driver information required during shipment
tracking.

Cached Information:

-   Driver Id
-   Driver Name
-   Latitude
-   Longitude

Benefits

-   Faster shipment tracking
-   Reduced database hits
-   No cross-service call required for every tracking request

------------------------------------------------------------------------

# Kafka Usage

Kafka provides asynchronous communication between services.

Example Events

-   Checkout Requested
-   Order Created
-   Payment Successful
-   Payment Failed
-   Driver Assigned

This eliminates tight coupling between services.

------------------------------------------------------------------------

# Database

PostgreSQL stores

-   Users
-   Drivers
-   Addresses
-   Shipments
-   Shipment Status History
-   Payments

Relationships

-   User → Address (One to Many)
-   User → Driver (One to One)
-   Shipment → Status History (One to Many)

------------------------------------------------------------------------

# Industry Practices Followed

-   Stateless Authentication
-   RESTful API Design
-   DTO Pattern
-   Layered Architecture
-   Repository Pattern
-   Builder Pattern
-   Dependency Injection
-   Validation
-   Exception Handling
-   Asynchronous Messaging
-   Caching
-   Loose Coupling
-   Shared Common Library

------------------------------------------------------------------------

# Future Enhancements

-   Spring Cloud Gateway
-   Eureka Service Registry
-   Notification Service
-   Email & SMS Notifications
-   Live Driver GPS Updates
-   Docker Compose Deployment
-   Kubernetes Deployment
-   CI/CD Pipeline
-   Prometheus & Grafana Monitoring
-   ELK Logging
-   Distributed Tracing

------------------------------------------------------------------------

# Getting Started

## Requirements

-   Java 17
-   Maven
-   PostgreSQL
-   Redis
-   Kafka
-   Docker

### Start Redis

``` bash
docker run -d --name redis -p 6379:6379 redis
```

### Start Kafka

Run Kafka using Docker.

### Build

``` bash
mvn clean install
```

### Run

``` bash
mvn spring-boot:run
```

------------------------------------------------------------------------

# Why This Project?

This project was designed to closely resemble an enterprise logistics
platform rather than a simple CRUD application. It demonstrates
authentication, authorization, asynchronous communication, caching,
payment processing, layered architecture, clean code principles and
scalable backend design.

------------------------------------------------------------------------

# Author

**Ashish Purohit**

Backend Developer \| Java \| Spring Boot \| Microservices
