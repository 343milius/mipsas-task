# mipsas-task
Spring boot application

## Requirements
* SpringBoot
* Java JDK 8+
* Maven

### Access to embedded DB
http://localhost:8080/h2-console

### Exercising Rest Controller
* **POST**	 /order			    Create new order		 http://localhost:8080/order
* **POST**	 /customer			Create new customer		 http://localhost:8080/customer
* **DELETE** /order/id    	    Delete the order	     http://localhost:8080/order/3

### Samples
* **POST** : http://localhost:8080/order/
```JSON
{
  "id": 3,
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
