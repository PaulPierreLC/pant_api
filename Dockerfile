#FROM maven:3.9-eclipse-temurin-17 AS deps
#WORKDIR /app
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
#FROM maven:3.9-eclipse-temurin-17 AS build
#WORKDIR /app
#COPY . .
#COPY --from=deps /root/.m2 /root/.m2
#RUN mvn clean package -DskipTests
#
#FROM eclipse-temurin:17-jdk
#WORKDIR /app
#ENV TZ=Europe/Paris
#COPY --from=build /app/target/*.jar app.jar
#ENTRYPOINT ["java", "-Duser.timezone=Europe/Paris", "-jar", "app.jar"]

# Utilise l'image officielle openjdk 18 comme base
FROM openjdk:18

# Crée un répertoire pour les logs
RUN mkdir -p /app/logs

# Copie le fichier JAR généré dans l'image
COPY target/*.jar /app/NOM_DU_JAR_CREE.jar

# Définit le point d'entrée du conteneur
ENTRYPOINT ["sh", "-c", "java -jar /app/*.jar"]

# Expose le port sur lequel l'application écoute
EXPOSE PORT_DU_PROJET

# Définit un volume pour les logs
VOLUME ["/app/logs"]
