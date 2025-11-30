# Multi-stage Dockerfile for Java Spring Boot application

# Stage 1: Build
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /workspace

COPY . .

# Make gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Stage 2: Runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /workspace/build/libs/*.jar app.jar

# Health check
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
    CMD java -version || exit 1

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
