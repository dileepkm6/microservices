FROM openjdk:11
ADD target/spring-eureka-server-0.0.1-SNAPSHOT.jar eurekaservice.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","eurekaservice.jar"]