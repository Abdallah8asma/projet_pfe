# Stage 1: Builder
FROM maven:3.6.3-openjdk-11-slim AS builder
ARG VERSION=3.5.0.0-SNAPSHOT
WORKDIR /build/

# Copy all files to build context
COPY . /build/

# Build each module sequentially
COPY socle/pom.xml /build/socle/
RUN mvn -f /build/socle/pom.xml clean install package

COPY socle-j2ee/pom.xml /build/socle-j2ee/
RUN mvn -f /build/socle-j2ee/pom.xml clean install package

COPY socle-j2ee-tiers/pom.xml /build/socle-j2ee-tiers/
RUN mvn -f /build/socle-j2ee-tiers/pom.xml clean install package

COPY scole-j2ee-mt/pom.xml /build/scole-j2ee-mt/
RUN mvn -f /build/scole-j2ee-mt/pom.xml clean install package

COPY mt-socle/pom.xml /build/mt-socle/
RUN mvn -f /build/mt-socle/pom.xml clean install package

COPY mt-commun/pom.xml /build/mt-commun/
RUN mvn -f /build/mt-commun/pom.xml clean install package

COPY mt-gpro-commun/pom.xml /build/mt-gpro-commun/
RUN mvn -f /build/mt-gpro-commun/pom.xml clean install package

COPY ma-gpro-logistique/pom.xml /build/ma-gpro-logistique/
RUN mvn -f /build/ma-gpro-logistique/pom.xml clean install package

# Copy the final WAR files to the target directory
COPY mt-gpro-commun/mt-gpro-commun-rest/target/mt-gpro-commun-rest-${VERSION}.war /build/target/
COPY ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-${VERSION}.war /build/target/

# Stage 2: Tomcat
FROM tomcat:9.0.88
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR files from the builder stage
COPY --from=builder /build/target/mt-gpro-commun-rest-${VERSION}.war ./mt-gpro-commun-rest.war
COPY --from=builder /build/target/ma-gpro-logistique-rest-${VERSION}.war ./ma-gpro-logistique-rest.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
