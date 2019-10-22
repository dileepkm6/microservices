FROM openjdk:11
ADD target/spring-config-server-0.0.1-SNAPSHOT.jar configservice.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","configservice.jar"]