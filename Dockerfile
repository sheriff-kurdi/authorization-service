FROM openjdk:17-jdk-slim-buster
COPY target/authorization-server-0.0.1-SNAPSHOT.jar authorizationserver.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/authorizationserver.jar"]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar authorizationserver.jar


