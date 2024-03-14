# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /leanPlateform
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /leanPlateform
COPY --from=build /leanPlateform/target/leanPlateform-0.0.1-SNAPSHOT.jar leanPlateform.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","leanPlateform.jar"]
