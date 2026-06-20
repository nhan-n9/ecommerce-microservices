FROM eclipse-temurin:21-jdk-alpine AS builder

RUN addgroup -S spring && adduser -S -G spring spring

WORKDIR /builder

RUN chown spring:spring /builder

USER spring:spring

COPY target/*.jar app.jar

RUN java -Djarmode=tools -jar app.jar extract --layers --destination extracted

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

ARG EXTRACTED_PATH=/builder/extracted

COPY --from=builder $EXTRACTED_PATH/dependencies/ ./
COPY --from=builder $EXTRACTED_PATH/spring-boot-loader/ ./
COPY --from=builder $EXTRACTED_PATH/snapshot-dependencies/ ./
COPY --from=builder $EXTRACTED_PATH/application/ ./

ENTRYPOINT ["java","-jar","/app.jar"]