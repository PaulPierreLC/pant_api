# Utilise l'image officielle openjdk 18 comme base
FROM openjdk:18

# Crée un répertoire pour les logs
RUN mkdir -p /app/logs

# Copie le fichier JAR généré dans l'image
COPY target/*.jar /app/projet_PANT.jar

# Définit le point d'entrée du conteneur
ENTRYPOINT ["sh", "-c", "java -jar /app/*.jar"]

# Expose le port sur lequel l'application écoute
EXPOSE 8079

# Définit un volume pour les logs
VOLUME ["/app/logs"]
