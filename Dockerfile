FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copia el jar
COPY target/franchise-api.jar app.jar

# Variables de entorno (fallback)
ENV SPRING_PROFILES_ACTIVE=dev

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]