FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
ENV SERVER_PORT 8081
COPY /src/main/resources/config.properties application.properties
COPY /target/user-service.jar user-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "user-service.jar"]