FROM openjdk:17-jdk-slim
EXPOSE 8100
ADD /target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
ENTRYPOINT ["java","-jar","/book-service.jar"]