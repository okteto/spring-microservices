FROM maven as build
WORKDIR /code
COPY . /code/
RUN mvn package

FROM openjdk:8-jre-alpine
COPY --from=build /code/target/department-service-1.1.jar department-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/department-service-1.1.jar"]