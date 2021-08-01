# Build Time
FROM maven:3.8-adoptopenjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Run Time
FROM adoptopenjdk/openjdk16:ubi
WORKDIR /app
COPY --from=build /app/target/*.jar service.jar
CMD ["java", "-jar", "/app/service.jar"]