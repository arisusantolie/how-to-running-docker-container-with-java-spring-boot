FROM adoptopenjdk/openjdk11
ENV PROFILE=dev
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} docker-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${PROFILE}","/docker-0.0.1-SNAPSHOT.jar"]