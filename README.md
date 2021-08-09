# GraphQL Netflix poc (POC)
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
  
  query($accountId:String!)
  {
    account(accountId:$accountId) 
    {
        accountId
        accountName
        balance
    }
    transactions(accountId:$accountId)
    {
        transactionId
        amount
        date
    }
  }
```
### QUERY VARIABLES

```javascript
  // IN QUERY VARIABLES EDITOR

  {  
    "accountId": "hvQ3b0uom9H6LdgO90Q12345asdfg"
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