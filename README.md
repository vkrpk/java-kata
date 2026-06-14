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

## Business rules

- Flash sales are only accessible during their active period.
- Flash sales have limited stock.
- Premium customers (SILVER, GOLD) benefit from early access 5 minutes before the sale starts.
- Standard customers gain premium access after 5 purchases.
- Priority score is calculated as: `membershipLevel.baseScore + (purchaseCount * 10)`
    - STANDARD: 0 base score
    - SILVER: 10 base score
    - GOLD: 25 base score

## Demo

Select a customer from the dropdown to see available flash sales.
Two flash sales are preloaded:
- **iPhone 15** — active now, visible to all customers
- **Samsung Galaxy S25** — starts in 3 minutes, visible to premium customers only

To demonstrate premium access: select Carol (STANDARD) and purchase iPhone 15 five times — she will then gain access to the Samsung flash sale.

## Testing

Run the test suite with:

```bash
mvn test
```
