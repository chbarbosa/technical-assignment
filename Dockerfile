FROM openjdk:11-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ARG JAR_FILE=target/my-application.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]