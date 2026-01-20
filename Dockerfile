# Build Stage
FROM gradle:8.5-jdk21 AS builder
WORKDIR /app

# Gradle 의존성 캐싱 최적화: 의존성 파일만 먼저 복사
COPY --chown=gradle:gradle build.gradle settings.gradle ./

# 의존성 다운로드 (소스 코드 변경 시에도 캐시 재사용)
RUN gradle dependencies --no-daemon || true

# 나머지 소스 코드 복사
COPY --chown=gradle:gradle src/ src/

# Gradle 빌드 (테스트 제외)
RUN gradle clean build --no-daemon -x test

# Run Stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# 비root 사용자 생성 및 사용
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# 빌드 스테이지에서 생성된 JAR 파일 복사
COPY --from=builder --chown=spring:spring /app/build/libs/*.jar spring-boot-boilerplate.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-boilerplate.jar"]
