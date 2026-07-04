# --- Etape 1 : compilation de l'application avec Maven ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# --- Etape 2 : image finale, legere, contenant uniquement le .jar ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/devops-backend.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
