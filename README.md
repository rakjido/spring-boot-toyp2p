# spring-boot-toyp2p

---
Toy P2P Crowd Funding

### Requirements Specification

```
* Member 
 - security + email (Optional)
 - socialAuth2

* REST API 
 - HATEOAS (Optional)
 - ResponseEntity
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
* QueryDSL
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


