# Stage 1: Build app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml dan download dependencies dulu (biar cache efisien)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy seluruh source code
COPY src ./src

# Build project (output .jar ke target/)
RUN mvn clean package -DskipTests

# Stage 2: Run app
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy hasil build dari stage pertama
COPY --from=build /app/target/*.jar app.jar

# Expose port Spring Boot (8083 sesuai application.properties)
EXPOSE 8083

# Jalankan aplikasi
ENTRYPOINT ["java","-jar","app.jar"]
