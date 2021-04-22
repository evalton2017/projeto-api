FROM openjdk:8
ADD target/seguradora-api-0.0.1-SNAPSHOT.jar seguradora-api.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "seguradora-api.jar"]