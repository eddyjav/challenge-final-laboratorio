FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msv-resultados

COPY ./pom.xml /app
COPY ./msv-resultados/.mvn ./.mvn
COPY ./msv-resultados/mvnw .
COPY ./msv-resultados/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msv-resultados/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msv-resultados/target/msv-resultados-0.0.1-SNAPSHOT.jar .

EXPOSE 8003

ENTRYPOINT ["java", "-jar", "msv-resultados-0.0.1-SNAPSHOT.jar"]