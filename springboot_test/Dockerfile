FROM openjdk:11
ADD target/userservice-0.0.1-SNAPSHOT.jar userservice.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","userservice.jar"]