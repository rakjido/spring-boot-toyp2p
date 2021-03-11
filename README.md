# spring-boot-toyp2p



spring boot toyP2P

### 필수기능

```
회원기능 
 - security + email
 - socialAuth2
REST API (https://sanghaklee.tistory.com/57)
 - HATEOAS
 - Reponse type
 - JWT (https://sanghaklee.tistory.com/47?category=790214)
Error Handling
Validation
ModelMapper
Log
```

### 필수기술

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

02. Account Vertical

- Account Entity, Repository, Service, ApiController with ResponseEntity

01. Initial Setting