FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY target/franchise-api.jar app.jar

ENV SPRING_PROFILES_ACTIVE=dev

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]