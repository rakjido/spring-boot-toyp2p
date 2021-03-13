# spring-boot-toyp2p


spring boot toyP2P

### Requirements Specification

```
* Member 
 - security + email
 - socialAuth2

* REST API 
 - HATEOAS
 - Reponse type
 - JWT 

* Log
  - Log text file
```

### Tech Stack

```
* Front end
  - HTML
  - CSS
  - Javascript
  - Ajax
* Spring Boot
* Gradle
* JPA
* Mysql, xtrabackup
* Redis caching
* Git & Github, Git Flow
* Jenkins CI
* nginx https
```


**application.yml**

```
spring:
  profiles:
    active: dev
```
choose one in (dev, stg, prd)

---

## Version

02. Account Vertical

- Account Entity, Repository, Service, ApiController with ResponseEntity

01. Initial Setting