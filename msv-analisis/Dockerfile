FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msv-analisis

COPY ./pom.xml /app
COPY ./msv-analisis/.mvn ./.mvn
COPY ./msv-analisis/mvnw .
COPY ./msv-analisis/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msv-analisis/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msv-analisis/target/msv-analisis-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "msv-analisis-0.0.1-SNAPSHOT.jar"]