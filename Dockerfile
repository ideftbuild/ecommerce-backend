# =================================================================================
# Stage 1: BUILDER - For creating the production JAR
# This stage builds the application, skipping tests.
# =================================================================================
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy only the files needed to download dependencies first
# This leverages Docker's layer caching
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts ./
COPY settings.gradle.kts ./

# Make the wrapper executable
RUN chmod +x ./gradlew

# Download dependencies
RUN ./gradlew dependencies

# Copy the rest of the source code
COPY src ./src

# Build the application, skipping the tests
# Tests can fail in this isolated environment because there's no database
RUN ./gradlew bootJar -x test

# =================================================================================
# Stage 2: DEVELOPMENT - For local development with live-reloading
# This stage runs the app directly using the Gradle wrapper.
# Source code will be mounted as a volume.
# =================================================================================
# FROM eclipse-temurin:21-jdk-jammy AS development
FROM gradle:8.14.3-jdk21 AS development

WORKDIR /app

# Copy the gradle wrapper and build files
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts ./
COPY settings.gradle.kts ./

# Make the wrapper executable
RUN chmod +x ./gradlew

EXPOSE 8080 8000

# Run the app with Gradle for live-reloading.
# Spring Boot Devtools will detect file changes and restart the app.
# The --no-daemon flag is recommended for containerized environments.
ENTRYPOINT ["./gradlew", "bootRun", "--no-daemon", "--args=--server.port=8080"]

# =================================================================================
# Stage 3: PRODUCTION - The final, lean production image
# This stage takes the built JAR from the 'builder' stage for a small footprint.
# =================================================================================
FROM eclipse-temurin:21-jre-jammy AS production

WORKDIR /app

# Copy the executable JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
