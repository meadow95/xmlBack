FROM openjdk:8-jdk-alpine
LABEL maintainer="Tim 47"
EXPOSE 8081
WORKDIR /app
COPY ./target/auth-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]