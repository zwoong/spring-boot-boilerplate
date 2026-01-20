# Spring Boot Boilerplate (Study)

> **Note:** 이 프로젝트는 [Genc/spring-boot-boilerplate](https://github.com/Genc/spring-boot-boilerplate)를 Fork하여 **학습 및 분석 목적**으로 구성된 리포지토리입니다.
> 원본 프로젝트의 아키텍처와 구성을 기반으로 실무 수준의 Spring Boot 사용법을 익히는 것을 목표로 합니다.

**Spring Boot Boilerplate**는 확장 가능하고 강력한 Spring Boot 애플리케이션 개발을 위한 시작점(Starter Kit)입니다.
최신 기술 스택을 기반으로 구성되어 있으며, 바로 실무에 적용 가능한 다양한 기능을 미리 설정해 두었습니다.

## 🎯 학습 목표 (Study Goals)

이 프로젝트를 통해 다음과 같은 핵심 Spring Boot 개념과 구조를 심도 있게 학습합니다.

1.  **전체적인 아키텍처 이해**: Layered Architecture의 실제 구현과 계층 간의 책임 분리
2.  **Spring Security & JWT**: 보안 필터 체인의 동작 원리와 토큰 기반 인증 시스템 구축
3.  **Entity-DTO 매핑 전략**: MapStruct를 활용한 효율적인 객체 변환 및 보일러플레이트 제거
4.  **표준 예외 처리**: `@ControllerAdvice`를 활용한 깔끔하고 통일된 API 예외 처리 방식

---

## ✨ 주요 기능

- **JWT 인증 시스템**: Spring Security와 JWT를 연동한 완벽한 보안 구성
- **표준화된 예외 처리**: Global Exception Handling을 통한 일관된 에러 응답
- **DTO 패턴 적용**: MapStruct를 활용한 Entity-DTO 간의 깔끔한 데이터 매핑
- **API 문서 자동화**: SpringDoc(Swagger)을 통한 REST API 문서화
- **DB 연동**: Spring Data JPA와 PostgreSQL 구성

## 🏗️ 아키텍처 (Architecture)

이 프로젝트는 유지보수성과 확장성을 고려하여 **Layered Architecture**를 채택하고 있습니다.

### 구조 (Directory Structure)

```text
src/main/java/com/farukgenc/boilerplate/springboot
├── controller      # API 엔드포인트 처리 (Req/Res)
├── service         # 비즈니스 로직 구현 (Transcation 관리)
├── repository      # 데이터 접근 계층 (JPA)
├── model           # JPA Entity 정의
├── security        # JWT 인증 및 보안 설정
├── exceptions      # 전역 예외 처리 (Global Exception Handling)
└── configuration   # Spring 설정 파일
```

### 디자인 패턴 및 주요 설계

*   **Controller-Service-Repository 패턴**: 각 계층의 역할을 명확히 분리하여 결합도를 낮추었습니다.
*   **DTO (Data Transfer Object) 패턴**: API 요청/응답 시 Entity를 직접 노출하지 않고 `Request/Response DTO`를 사용하여 보안과 안정성을 높였습니다.
*   **Mapper 패턴**: `MapStruct`를 사용하여 Entity와 DTO 간의 변환 로직을 자동화하고, 보일러플레이트 코드를 최소화했습니다.
*   **Global Exception Handling**: `@ControllerAdvice`를 활용하여 애플리케이션 전역에서 발생하는 예외를 일관된 포맷으로 클라이언트에게 반환합니다.

## 🛠 기술 스택

- **Java**: JDK 21
- **Framework**: Spring Boot (v4.0.1)
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Security**: Spring Security + JWT (Auth0)
- **Mapping**: MapStruct
- **Utils**: Lombok, Apache Commons
- **Documentation**: Swagger (OpenAPI 3.0)

## 🚀 시작하기 (Getting Started)

### 사전 요구사항 (Prerequisites)

이 프로젝트를 실행하기 위해 다음 환경이 준비되어 있어야 합니다.

*   JDK 21 이상
*   Gradle (또는 포함된 Gradle Wrapper 사용)
*   PostgreSQL
*   Docker (선택 사항)

### 실행 방법

1.  **데이터베이스 실행**
    Docker를 사용한다면 손쉽게 DB를 실행할 수 있습니다.
    ```bash
    docker compose up -d
    ```
    (로컬 환경 설정이 다르다면 `local-docker-compose.yml`을 확인하세요.)

2.  **프로젝트 빌드**
    프로젝트 루트 디렉토리에서 다음 명령어로 의존성을 설치하고 빌드합니다.
    ```bash
    ./gradlew clean build
    ```
    (Windows의 경우 `gradlew.bat clean build`)

3.  **애플리케이션 실행**
    빌드가 완료되면 `build/libs` 디렉토리의 JAR 파일을 실행하거나, IDE에서 바로 실행할 수 있습니다.
    ```bash
    java -jar build/libs/spring-boot-boilerplate-0.0.1-SNAPSHOT.jar
    ```

## ⚙️ 설정 및 커스터마이징

주요 설정은 `src/main/resources/application.yml` 파일에서 관리합니다.

*   **JWT 토큰 설정**: 비밀 키, 발급자, 만료 시간 등을 수정할 수 있습니다.
*   **데이터베이스 설정**: DB URL, Username, Password를 환경에 맞게 변경하세요.
*   **Swagger 설정**: API 문서 관련 경로 및 설정을 변경할 수 있습니다.
*   **보안 예외 경로**: `SecurityConfiguration.java` 파일에서 인증 없이 접근 가능한 엔드포인트를 관리할 수 있습니다.

## 📚 API 문서 (Swagger)

애플리케이션 실행 후, 아래 주소에서 API 문서를 확인할 수 있습니다.
*   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) (기본 포트 사용 시)

## 🔗 참고 리소스

*   **Postman 컬렉션**: [Postman Collection 바로가기](https://www.postman.com/postmanfaruk/workspace/faruk-genc-projects/collection/11439300-3d0317df-f217-40ff-a2a6-4eaaf66e1c55?action=share&creator=11439300) - API 테스트를 위한 컬렉션을 제공합니다.

## 📄 라이선스

Apache License 2.0
