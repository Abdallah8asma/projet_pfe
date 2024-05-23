# Dockerfile.ma-gpro-logistique
FROM maven:3.6.3-openjdk-11-slim AS BUILDER
ARG VERSION=3.5.0.0-SNAPSHOT
WORKDIR /build/
COPY . /build/
# socle
COPY socle/pom.xml /build/
RUN mvn clean install
# socle-j2ee
COPY socle-j2ee/pom.xml /build/socle-j2ee/
RUN mvn clean install
# socle-j2ee-tiers
COPY socle-j2ee-tiers/pom.xml /build/socle-j2ee-tiers/
RUN mvn clean install
# scole-j2ee-mt
COPY scole-j2ee-mt/pom.xml /build/scole-j2ee-mt/
RUN mvn clean install
# mt-socle
COPY mt-socle/pom.xml /build/mt-socle/
RUN mvn clean install
# mt-commun
COPY mt-commun/pom.xml /build/mt-commun/
RUN mvn clean install
# mt-gpro-commun
COPY mt-gpro-commun/pom.xml /build/mt-gpro-commun/
RUN mvn clean install
# ma-gpro-logistique
COPY ma-gpro-logistique/pom.xml /build/ma-gpro-logistique/
RUN mvn clean install
# Build the ma-gpro-logistique-rest WAR
RUN mvn clean package -pl ma-gpro-logistique/ma-gpro-logistique-rest

# Utilisez l'image Tomcat officielle comme base
FROM tomcat:9.0.88
# Copiez le WAR dans le répertoire webapps de Tomcat
WORKDIR /opt/
COPY --from=BUILDER /build/ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-${VERSION}.war /opt/tomcat/latest/webapps/

# Exposez le port sur lequel Tomcat écoute (par défaut : 8080)
EXPOSE 8080

# Commande par défaut pour démarrer Tomcat lors de l'exécution du conteneur
CMD ["catalina.sh", "run"]
