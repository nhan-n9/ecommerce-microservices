FROM eclipse-temurin:21-jdk-alpine AS builder

RUN groupadd -r spring && useradd -r -g spring spring

USER spring:spring

WORKDIR /builder

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

RUN java -Djarmode=tools -jar app.jar extract --layers --destination extracted

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

ARG EXTRACTED_PATH=/builder/extracted

COPY --from=builder $EXTRACTED_PATH/dependencies/ ./
COPY --from=builder $EXTRACTED_PATH/spring-boot-loader/ ./
COPY --from=builder $EXTRACTED_PATH/snapshot-dependencies/ ./
COPY --from=builder $EXTRACTED_PATH/application/ ./

ENTRYPOINT ["java","-jar","/app.jar"]