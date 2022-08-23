FROM openjdk:17
COPY target/authorization-server-0.0.1-SNAPSHOT.jar authorization-server-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/authorization-server-0.0.1-SNAPSHOT.jar"]
