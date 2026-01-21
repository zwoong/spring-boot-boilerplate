# 1단계: 빌드 스테이지 (빌드 속도 최적화)
FROM gradle:8.5-jdk21 AS builder
WORKDIR /app

# Gradle Wrapper 및 빌드 설정 파일 복사 (Gradle Wrapper 사용을 위해 필요)
COPY --chown=gradle:gradle gradlew gradlew.bat ./
COPY --chown=gradle:gradle gradle/wrapper/ gradle/wrapper/
COPY --chown=gradle:gradle build.gradle settings.gradle ./

# Gradle Wrapper 실행 권한 부여 및 의존성 다운로드 (소스 수정 시 매번 다운로드 방지)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon || true

# 나머지 전체 소스 복사 및 빌드
COPY --chown=gradle:gradle . .
RUN ./gradlew build --no-daemon -x test

# 2단계: 실행 스테이지 (용량 및 보안 최적화)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# spring 사용자 생성 및 전환 (보안)
RUN useradd -m spring
USER spring

# 빌드 결과물만 'app.jar'라는 이름으로 복사
COPY --from=builder --chown=spring:spring /app/build/libs/*.jar app.jar

EXPOSE 8080

# 별도의 프로파일 지정 없이 기본 실행
ENTRYPOINT ["java", "-jar", "app.jar"]