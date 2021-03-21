FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=kafka-producer.jar
ADD ${JAR_FILE} kafka-producer.jar
ENTRYPOINT ["java","-jar","/kafka-producer.jar"]