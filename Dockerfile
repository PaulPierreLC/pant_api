FROM maven:3.9-eclipse-temurin-17 AS deps
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
COPY --from=deps /root/.m2 /root/.m2
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
ENV TZ=Europe/Paris
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone=Europe/Paris", "-jar", "app.jar"]