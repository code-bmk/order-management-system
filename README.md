# Zooplus Order Management Service

## Local System Requirements

For building and running the application you need:

- JDK 11
- Maven 3

## Steps to run the application locally


1. Build the project using
  `mvn clean install`
  
2. There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main
` method in the `com.zooplus.orderManagementService.OrderManagementServiceApplication` class from your IDE.

3. Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

4. The web application is accessible via localhost:8080


