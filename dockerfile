FROM maven:3.6.3-openjdk-11-slim AS BUILDER
ARG VERSION=3.5.0.0-SNAPSHOT
WORKDIR /build/
COPY . /build/
#socle
COPY socle/pom.xml /build/
RUN mvn clean install package
#socle-j2ee
COPY socle-j2ee/pom.xml /build/socle-j2ee/
RUN mvn clean install package
#socle-j2ee-tiers
COPY socle-j2ee-tiers/pom.xml /build/socle-j2ee-tiers/
RUN mvn clean install package
#scole-j2ee-mt
COPY scole-j2ee-mt/pom.xml /build/scole-j2ee-mt/
RUN mvn clean install package
#mt-socle
COPY mt-socle/pom.xml /build/mt-socle/
RUN mvn clean install package
#mt-commun
COPY mt-commun/pom.xml   /build/mt-commun/
RUN mvn clean install package
#mt-gpro-commun
COPY mt-gpro-commun/pom.xml /build/mt-gpro-commun/
RUN mvn clean install package
#ma-gpro-logistique
COPY ma-gpro-logistique/pom.xml /build/ma-gpro-logistique/
RUN mvn clean install package

# Copy the final WAR files to the target directory
COPY mt-gpro-commun/mt-gpro-commun-rest/target/mt-gpro-commun-rest-${VERSION}.war /build/target/
COPY ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-${VERSION}.war /build/target/

# Tomcat
FROM tomcat:9.0.88
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR files from the builder stage
COPY --from=builder /build/target/mt-gpro-commun-rest-${VERSION}.war ./mt-gpro-commun-rest.war
COPY --from=builder /build/target/ma-gpro-logistique-rest-${VERSION}.war ./ma-gpro-logistique-rest.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
