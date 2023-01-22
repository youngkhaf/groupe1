#Image de base
FROM openjdk:17-alpine
LABEL maintainer="youngkhaf@gmail.com"
VOLUME /groupe1-data
ADD target/groupe1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
#java -jar
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar", "/app.jar"]