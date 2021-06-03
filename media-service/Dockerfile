FROM openjdk:8-jdk-alpine
LABEL maintainer="Tim 47"
EXPOSE 8085
WORKDIR /app
COPY ./target/media-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "media-service-0.0.1-SNAPSHOT.jar"]