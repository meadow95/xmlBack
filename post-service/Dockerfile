FROM openjdk:8-jdk-alpine
LABEL maintainer="Tim 47"
EXPOSE 8084
WORKDIR /app
COPY ./target/post-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "post-service-0.0.1-SNAPSHOT.jar"]