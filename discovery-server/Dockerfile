FROM openjdk:8-jdk-alpine
LABEL maintainer="Tim 47"
EXPOSE 8761
WORKDIR /app
COPY ./target/discovery-server-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "discovery-server-0.0.1-SNAPSHOT.jar"]