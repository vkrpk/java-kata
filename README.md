# Flash Sales Application

Example project implementing a Flash Sales system using:

* Java 25
* Spring Boot
* Vue.js
* Clean Architecture principles

## Architecture

The application is structured around Clean Architecture:

* **Domain**: business entities and business rules
* **Application**: use cases
* **Infrastructure**: repositories and REST controllers
* **Frontend**: Vue.js user interface

The focus of this implementation is on business logic rather than technical infrastructure.

## Running the application

### Backend

```bash
mvn spring-boot:run
```

The API will be available on:

```text
http://localhost:8080
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The UI will be available on:

```text
http://localhost:5173
```

## Demo data

The application starts with preloaded demo data.

To test the purchase flow, use the following flash sale identifier:

Customers:
- Alice (GOLD): 00000000-0000-0000-0000-000000000001
- Bob (STANDARD): 00000000-0000-0000-0000-000000000002

Flash sale:
- iPhone 15: 00000000-0000-0000-0000-000000000010

This flash sale corresponds to the latest iPhone available in a flash sale.

## Assumptions

As this exercise is time-boxed, repositories are implemented in memory to focus on business rules and Clean Architecture concepts.

The main business rules implemented are:

* Flash sales are only accessible during their active period.
* Flash sales have limited stock.
* Premium customers benefit from priority access.
* Priority is determined by membership level and purchase history.

## Testing

Run the test suite with:

```bash
mvn test
```
