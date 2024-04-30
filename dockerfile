FROM maven:3.6.3-openjdk-11-slim AS BUILDER
ARG VERSION=3.5.0.0-SNAPSHOT
WORKDIR /build/
COPY . /build/
COPY socle/pom.xml /build/
RUN mvn clean install package
COPY socle-j2ee/pom.xml /build/socle-j2ee/
RUN mvn clean install package
COPY socle-j2ee-tiers/pom.xml /build/socle-j2ee-tiers/
RUN mvn clean install package
COPY scole-j2ee-mt/pom.xml /build/scole-j2ee-mt/
RUN mvn clean install package
COPY mt-socle/pom.xml /build/mt-socle/
RUN mvn clean install package
COPY mt-commun/pom.xml   /build/mt-commun/
RUN mvn clean install package
COPY mt-gpro-commun/pom.xml /build/mt-gpro-commun/
RUN mvn clean install package
COPY ma-gpro-logistique/pom.xml /build/ma-gpro-logistique/
RUN mvn clean install package
COPY mt-gpro-commun/mt-gpro-commun-rest/target/mt-gpro-commun-rest-${VERSION}.war target/mt-gpro-commun.war
COPY ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-${VERSION}.war target/ma-gpro-logistique.war

FROM openjdk:11.0.11-jre-slim
WORKDIR /app/
COPY --from=BUILDER /build/target/mt-gpro-commun.war /app/
COPY --from=BUILDER /build/target/ma-gpro-logistique.war /app/
CMD java -jar /app/mt-gpro-commun.war
CMD java -jar /app/ma-gpro-logistique.war
