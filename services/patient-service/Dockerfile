FROM eclipse-temurin:17-jre
WORKDIR /app
COPY ./target/patient-service-2.0.0.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]