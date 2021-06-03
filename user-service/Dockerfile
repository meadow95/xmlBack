FROM openjdk:8-jdk-alpine
LABEL maintainer="Tim 47"
EXPOSE 8083
WORKDIR /app
COPY ./target/user-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]