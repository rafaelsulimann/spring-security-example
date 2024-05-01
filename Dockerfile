FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-slim
WORKDIR /app
ENV POSTGRES_HOST=localhost
ENV POSTGRES_PORT=5432
ENV POSTGRES_DATABASE=test
ENV APP_PROFILE=test
ENV CLIENT_ID=myclientid
ENV CLIENT_SECRET=myclientsecret
ENV JWT_SECRET=myjwtsecret
ENV JWT_DURATION=86400
ENV CORS_ORIGINS="https://sulimann.com.br,http://localhost:3000,http://localhost:5173,"
ENV CLIENT_SECRET=myclientsecret
COPY --from=builder /app/target/springsecurityexample-0.0.1-SNAPSHOT.jar ./springsecurityexample.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/springsecurityexample.jar"]