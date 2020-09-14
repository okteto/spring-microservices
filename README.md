# Microservices with Spring Boot and Spring Cloud on Kubernetes Demo Project

The sample microservices-based system consists of the following modules:

- **gateway-service**: a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **employee-service**: a module containing the first of our sample microservices that allows to perform CRUD operation on Mongo repository of employees
- **department-service**: a module containing the second of our sample microservices that allows to perform CRUD operation on Mongo repository of departments. It communicates with employee-service. 
- **organization-service**: a module containing the third of our sample microservices that allows to perform CRUD operation on Mongo repository of organizations. It communicates with both employee-service and department-service.
- **admin-service**: a module containing embedded Spring Boot Admin Server used for monitoring Spring Boot microservices running on Kubernetes

The following picture illustrates the architecture described above including Kubernetes objects.

<img src="https://piotrminkowski.files.wordpress.com/2018/07/micro-kube-1.png" title="Architecture1">

## Tutorial

- Deploy the **Sample App** on your personal namespace by clicking on the following button:

<p align="center">
<a href="https://cloud.okteto.com/deploy">
  <img src="https://okteto.com/develop-okteto.svg" alt="Develop on Okteto">
</a>
</p>

- To develop on the **admin** service:

```
    $ okteto up -f admin-service/okteto.yml
      ✓  Development container activated
      ✓  Files synchronized
         Namespace: githubid
         Name:      admin
         Forward:   5005 -> 5005

    Welcome to your development environment. Happy coding!
    githubid:admin okteto> mvn spring-boot:run
```

- To develop on the **department** service:

```
    $ okteto up -f department-service/okteto.yml
      ✓  Development container activated
      ✓  Files synchronized
         Namespace: githubid
         Name:      department
         Forward:   5005 -> 5005

    Welcome to your development environment. Happy coding!
    githubid:department okteto> mvn spring-boot:run
```

- To develop on the **employee** service:

```
    $ okteto up -f employee-service/okteto.yml
      ✓  Development container activated
      ✓  Files synchronized
         Namespace: githubid
         Name:      employee
         Forward:   5005 -> 5005

    Welcome to your development environment. Happy coding!
    githubid:employee okteto> mvn spring-boot:run
```

- To develop on the **gateway** service:

```
    $ okteto up -f gateway-service/okteto.yml
      ✓  Development container activated
      ✓  Files synchronized
         Namespace: githubid
         Name:      gateway
         Forward:   5005 -> 5005

    Welcome to your development environment. Happy coding!
    githubid:gateway okteto> mvn spring-boot:run
```

- To develop on the **organization** service:

```
    $ okteto up -f organization-service/okteto.yml
      ✓  Development container activated
      ✓  Files synchronized
         Namespace: githubid
         Name:      organization
         Forward:   5005 -> 5005

    Welcome to your development environment. Happy coding!
    githubid:organization okteto> mvn spring-boot:run
```
