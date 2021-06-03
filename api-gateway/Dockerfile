FROM openjdk:8-jdk-alpine
LABEL maintainer="Tim 47"
EXPOSE 8080
WORKDIR /app
COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]