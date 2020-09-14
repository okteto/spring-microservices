FROM openjdk:8-jre-alpine
ADD target/employee-service-1.1.jar employee-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/employee-service-1.1.jar"]