# quarkus-carservice-api Project

### Test stack

- Quarkus and Quarkus DevServices (for dev & test DB using testcontainers)
- Hibernate ORM using Panache entities
- Redis caching
- Flyway (for DB initialization)
- Tests using Junit 5/Jupiter
- Gradle package mgmt

### Prerequisites

Install Podman and follow these steps so that the testcontainers use podman instead of docker:

https://quarkus.io/blog/quarkus-devservices-testcontainers-podman/

### Getting started

API built using Quarkus and PostgreSQL as its backing DB

1. Run the API in a mode that allows live coding (very cool by the way!)
```
./gradlew quarkusDev
```

2. Run some Curls to validate entities are loaded
```
curl localhost:8081/make
curl localhost:8081/make/models
```

## Quarkus Original Docs
### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

### Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-carservice-api-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.
