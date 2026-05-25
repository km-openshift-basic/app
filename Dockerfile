FROM registry.access.redhat.com/ubi8/openjdk-17:1.20 AS builder

USER root
WORKDIR /build
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests -Dmaven.javadoc.skip=true

FROM registry.access.redhat.com/ubi8/openjdk-17-runtime:1.20

ENV LANGUAGE='en_US:en'

COPY --from=builder /build/target/quarkus-app/lib/ /deployments/lib/
COPY --from=builder /build/target/quarkus-app/*.jar /deployments/
COPY --from=builder /build/target/quarkus-app/app/ /deployments/app/
COPY --from=builder /build/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]
