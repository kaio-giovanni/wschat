# WSCHAT

This project uses websocket with spring boot to create a realtime chat 

### Dependencies
- Git
- Java
- Gradle
- Docker
- Npm

### Setup

- Create an account in [auth0 website](https://auth0.com/)
  - Create a JavaScript application on dashboard and get app credentials (domain and client id)
  - Set allowed origins (localhost:3000, for example)
  - Put your auth0 credentials in the [JS code](frontend/js/auth.js)
  - Put your Json Web Key Set Url in the `.env` file [example](.env.example)
- Install [http-server](https://www.npmjs.com/package/http-server) globally

### Run project

- Create a redis container

```bash

docker-compose up 
```

- Run the backend

```bash

gradle bootRun
```

- Run the [frontend](frontend)

```bash

http-server . -p 3000
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.1/gradle-plugin/reference/html/)
* [WebSocket](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#messaging.websockets)

### Guides
The following guides illustrate how to use some features concretely:

* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)

### Author

| ![user](https://avatars1.githubusercontent.com/u/64810260?v=4&s=150)                    |
| --------------------------------------------------------------------------------------- |
| <p align="center"> <a href="https://github.com/kaio-giovanni"> @kaio-giovanni </a> </p> |