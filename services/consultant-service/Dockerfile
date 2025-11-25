FROM eclipse-temurin:17-jre
WORKDIR /app
COPY ./target/consultant-service-2.0.0.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]