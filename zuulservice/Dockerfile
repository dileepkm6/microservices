FROM openjdk:11
ADD target/zuulservice-0.0.1-SNAPSHOT.jar zuulservice.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","zuulservice.jar"]