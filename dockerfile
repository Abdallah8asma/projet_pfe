#FROM openjdk:11 

#MAINTAINER asmaabdallah518@gmail.com

#RUN mkdir -p /app

#WORKDIR /app

#COPY . /app

#COPY /app/ma-gpro-logistique/ma-gpro-logistique-rest/target/mt-gpro-commun-rest-3.5.0.0-SNAPSHOT.war  /app/app.war

#EXPOSE 8080

#ENTRYPOINT ["java","-jar","/app/app.war"]


# Utilisez une image de base contenant Java et Maven
FROM maven:3.8.1-jdk-11 AS build

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier pom.xml pour installer les dépendances
COPY /socle/pom.xml .
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

COPY /socle-j2ee/pom.xml .
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

COPY /socle-j2ee-tiers/pom.xml .
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

COPY /scole-j2ee-mt/pom.xml .
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

COPY /mt-socle/pom.xml .
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

COPY /mt-commun/pom.xml . 
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

COPY /mt-gpro-commun/pom.xml .

# Installez les dépendances
RUN mvn dependency:go-offline


# Compilez l'application
RUN mvn package -DskipTests

# Utilisez une image de base légère pour exécuter l'application
FROM openjdk:11-jre-slim

# Copiez le fichier JAR compilé dans le conteneur
COPY --from=build /app/target/*.war /app/application.war

# Exposez le port sur lequel l'application Spring Boot écoute
EXPOSE 8080

# Commande pour démarrer l'application Spring Boot
CMD ["java", "-jar", "/app/application.war"]
