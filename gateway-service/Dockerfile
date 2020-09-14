FROM openjdk:8-jre-alpine
ADD target/gateway-service-1.1.jar gateway-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/gateway-service-1.1.jar"]