# Build Stage
FROM gradle:8.5-jdk21 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . .
# Gradle 빌드 (테스트 제외)
RUN gradle clean build --no-daemon -x test

# Run Stage
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# 빌드 스테이지에서 생성된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar spring-boot-boilerplate.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-boilerplate.jar"]
