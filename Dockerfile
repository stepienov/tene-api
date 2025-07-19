FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
