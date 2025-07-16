FROM openjdk:24-oracle

WORKDIR /app

COPY target/book-service-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]