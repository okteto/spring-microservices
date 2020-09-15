# syntax=docker/dockerfile:experimental
FROM maven as build
WORKDIR /code
COPY . /code/
RUN --mount=type=cache,target=/root/.m2 mvn package

FROM openjdk:8-jre-alpine
COPY --from=build /code/target/organization-service-1.1.jar organization-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/organization-service-1.1.jar"]