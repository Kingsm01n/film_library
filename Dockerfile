FROM openjdk:11
COPY target/*.jar application.jar
EXPOSE ${SERVER_PORT:-8080}
ENTRYPOINT ["java", "-jar", "application.jar"]