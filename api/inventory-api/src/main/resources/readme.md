### 배포할 api의 리소스 폴더에 application.yml 작성
```
spring:
  datasource:
    url:
    username:
    password:
    driver-class-name: org.mariadb.jdbc.Driver

  jpa:
    database: mysql
    hibernate:
      ddl-auto: validate
    generate-ddl: false

  flyway:
    url: 
    user: 
    password: 

  security:
    user:
      password:


sharp:
  auth-token:
    secret:
    refresh-token-secret:

    access-token-expired-mile-seconds:
    refresh-token-expired-mile-seconds:

    role-key: 'ROLES'
    prefix: 'Bearer '
```

### 테스트를 위한 application-test.yml 작성
```
sharp:
  test:
    auth-token: 'Bearer <사용자 지정 키를 사용하여 jwt.io 등의 플랫폼, 유틸로 토큰을 발급해 사용한다.'
```
