# GraphQL Netflix POC
The intent of this **POC** is to demonstrate the use of GraphQL DGS client in conjunction with webFlux and async R2DBC driver.

## Run
mvn spring-boot:run

## GraphQL Endpoint

[http://localhost:8080/graphiql](http://localhost:8080/graphiql)

1. Sign in with admin credentials: username: admin, password: test1234 (credentials can be changed in application.properties)
  
1. To test add the following queries into the left hand pane.

### Get Account and Transactions
```javascript
  // IN QUERY EDITOR
  
  query ($customerId: String!) {
    customer(customerId: $customerId) {
      customerId
      firstName
      surname
    }
    accounts(customerId: $customerId) {
      accountId
      accountName
      balance
      customerId
      transactions {
        transactionId
        amount
        date
        accountId
      }
    }
  }
	
```
### QUERY VARIABLES

```javascript
  // IN QUERY VARIABLES EDITOR

  {  
      "customerId": "hvQ3b0uom9H6LdgO90Q12345asdf1"
  }

```
### REQUEST HEADERS 

```javascript
// IN REQUEST HEADERS TAB

  {
    "Content-Type": "application/json",
    "Authorization": "Basic YWRtaW46dGVzdDEyMzQ="
  }
```

note: at the time of development there are known issues with setting spring.webflux.base-path property and using the graphiql client