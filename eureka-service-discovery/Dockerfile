FROM openjdk:17-jdk-slim
EXPOSE 8761
ADD /target/eureka-service-discovery-0.0.1-SNAPSHOT.jar eureka-service-discovery.jar
ENTRYPOINT ["java","-jar","/eureka-service-discovery.jar"]