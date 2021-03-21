FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=kafka-consumer.jar
ADD ${JAR_FILE} kafka-consumer.jar
ENTRYPOINT ["java","-jar","/kafka-consumer.jar"]