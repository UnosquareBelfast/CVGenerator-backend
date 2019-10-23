FROM gradle:5.4.1-jdk8-alpine AS build_stage

COPY --chown=gradle:gradle . /project
WORKDIR /project
COPY build.gradle settings.gradle ./
COPY src/ src/

RUN gradle clean build -x test -x integTest


FROM openjdk:8-jre-alpine

COPY --from=build_stage /project/build/libs/*.jar ./build/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/app.jar"]