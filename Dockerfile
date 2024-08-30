
FROM openjdk:17-jdk-alpine
ENV DB_NAME=login-auth-api \
    DB_PASSWORD=12345 \
    DB_URL=jdbc:h2:mem:testdb \
    DB_USERNAME=sa \
    TOKEN=adkoasdowdkawopdkapsdk
RUN mkdir /app
WORKDIR /app
COPY pom.xml /app
COPY target/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]

