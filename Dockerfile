FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/app
COPY /target/user-service.jar user-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-service.jar"]