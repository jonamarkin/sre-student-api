# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /workspace/app

COPY pom.xml .
# Build dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application and build it
COPY src src
RUN mvn package -DskipTests

# Stage 2: Production stage
FROM eclipse-temurin:17-jdk-alpine AS production
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /workspace/app/target/*.jar ./app.jar

# Expose port 8080
EXPOSE 8080

# Define entry point to run the Spring Boot application
# Also entry point is the recommended approach for Java compared to CMD
ENTRYPOINT ["java", "-jar", "app.jar"]

