FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD /target/greeting-service-0.0.1-SNAPSHOT.jar greeting-service.jar
ENTRYPOINT ["java","-jar","/greeting-service.jar"]