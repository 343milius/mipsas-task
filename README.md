# mipsas-task
Spring boot application

## Requirements
* SpringBoot
* Java JDK 8+
* Maven

### Access to embedded DB
http://localhost:8080/h2-console

### Exercising Rest Controller
Orders:
* **POST**	 /order			    
  * Create new order
* **DELETE** /order/{id}    
  * Delete the order
* **GET**	 /order			    
  * See all orders
* **PATCH**  /order/{orderId}/customer/{customerId} 
  * Update order for customer using customer id
* **PATCH**  /order/{orderId}
  * Update order for customer using customer JSON body

Customers:
* **POST**	 /customer			
  * Create new customer		 
* **GET**	 /customer/{id}
  * See single customer		 
* **GET**	 /customer			
  * See all customers		 

### Samples
* **POST** : http://localhost:8080/order/
```JSON
{
  "type": "BLANKET",
  "description": "Regular order",
  "activeFrom": "2016-08-13",
  "activeTo": "2016-10-13",
  "customer": {
    "id" : 1
  }
}
```
* **DELETE** : http://localhost:8080/order/3
