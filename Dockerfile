# Use a Maven image to build
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set work directory
WORKDIR /app

# Copy project files (pom.xml first for better caching)
COPY ./.mvn .mvn
COPY ./pom.xml .
COPY ./src ./src
# Download dependencies first (cache optimization)
RUN mvn dependency:go-offline
# Package the application
RUN mvn package -DskipTests

# Now create a minimal runtime image
FROM registry.access.redhat.com/ubi8/openjdk-21:1.18

ENV LANGUAGE='en_US:en'


# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185 --from=build /app/target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 --from=build /app/target/quarkus-app/*.jar /deployments/
COPY --chown=185 --from=build /app/target/quarkus-app/app/ /deployments/app/
COPY --chown=185 --from=build /app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]

