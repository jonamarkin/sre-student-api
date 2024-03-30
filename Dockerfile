#FROM eclipse-temurin:17-jdk-alpine as build
#WORKDIR /workspace/app
#
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN mvn clean package -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#ARG DEPENDENCY=/workspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
##ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.markin.studentmanagement.StudentManagementApplication"]


# Stage 1: Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /workspace/app

# Copy only the POM file to cache dependencies
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
ENTRYPOINT ["java", "-jar", "app.jar"]

