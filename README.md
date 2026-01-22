# Spring Boot Boilerplate (Study)

> **Note:** 이 프로젝트는 [Genc/spring-boot-boilerplate](https://github.com/Genc/spring-boot-boilerplate)를 Fork하여 **학습 및 분석 목적**으로 구성된 리포지토리입니다.
> 원본 프로젝트의 아키텍처와 구성을 기반으로 실무 수준의 Spring Boot 사용법을 익히는 것을 목표로 합니다.

**Spring Boot Boilerplate**는 확장 가능하고 강력한 Spring Boot 애플리케이션 개발을 위한 시작점(Starter Kit)입니다.
최신 기술 스택을 기반으로 구성되어 있으며, 바로 실무에 적용 가능한 다양한 기능을 미리 설정해 두었습니다.

## 🎯 학습 목표 (Study Goals)

이 프로젝트를 통해 다음과 같은 핵심 Spring Boot 개념과 구조를 심도 있게 학습합니다.

1.  **4-Layered Architecture**: 계층별 책임 분리와 의존성 방향 이해
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

이 프로젝트는 유지보수성과 확장성을 고려하여 **4-Layered Architecture**를 채택하고 있습니다.

### 4-Layered Architecture 개요

4-Layered Architecture는 애플리케이션을 4개의 명확한 계층으로 분리하여 각 계층의 책임을 명확히 하고, 계층 간 의존성을 단방향으로 유지하는 아키텍처 패턴입니다.

```
┌─────────────────────────────────────────────────────────┐
│  1. Presentation Layer (표현 계층)                      │
│     - HTTP 요청/응답 처리                                 │
│     - DTO 변환 및 유효성 검증                             │
│     - 예외 처리                                          │
└─────────────────┬───────────────────────────────────────┘
                  │ 의존
┌─────────────────▼───────────────────────────────────────┐
│  2. Application/Business Layer (애플리케이션/비즈니스 계층)│
│     - 비즈니스 로직 구현                                  │
│     - 트랜잭션 관리                                       │
│     - 도메인 서비스 조합                                  │
└─────────────────┬───────────────────────────────────────┘
                  │ 의존
┌─────────────────▼───────────────────────────────────────┐
│  3. Domain Layer (도메인 계층)                           │
│     - 도메인 엔티티 (순수 도메인 모델)                    │
│     - 도메인 규칙 및 비즈니스 규칙                       │
│     - 다른 계층에 의존하지 않음                           │
└─────────────────┬───────────────────────────────────────┘
                  │ 의존
┌─────────────────▼───────────────────────────────────────┐
│  4. Infrastructure/Data Access Layer (인프라/데이터 접근 계층)│
│     - 데이터 접근 (Repository)                           │
│     - 외부 시스템 연동 (Security, Mapper 등)              │
│     - Spring 설정 및 유틸리티                             │
└─────────────────────────────────────────────────────────┘
```

### 계층별 상세 구조 (Directory Structure)

```text
src/main/java/com/farukgenc/boilerplate/springboot/
│
├── 📁 controller/                    # 1. Presentation Layer
│   ├── auth/                         # 도메인별 컨트롤러 그룹화
│   │   ├── LoginController.java      # 로그인 API 엔드포인트
│   │   └── RegistrationController.java # 회원가입 API 엔드포인트
│   └── HelloController.java          # 일반 컨트롤러
│
├── 📁 dto/                           # 1. Presentation Layer
│   ├── auth/                         # 인증 관련 DTO
│   │   ├── request/                  # 요청 DTO
│   │   │   ├── LoginRequest.java
│   │   │   └── RegistrationRequest.java
│   │   └── response/                 # 응답 DTO
│   │       ├── LoginResponse.java
│   │       └── RegistrationResponse.java
│   └── user/                          # 사용자 관련 DTO
│       └── AuthenticatedUserDto.java
│
├── 📁 exceptions/                     # 1. Presentation Layer
│   ├── AuthControllerAdvice.java     # 인증 관련 예외 처리
│   ├── ValidationAdvice.java         # 유효성 검증 예외 처리
│   ├── ApiExceptionResponse.java     # 예외 응답 DTO
│   ├── ValidationErrorResponse.java  # 유효성 검증 에러 응답
│   └── RegistrationException.java    # 도메인 예외
│
├── 📁 service/                        # 2. Application/Business Layer
│   └── user/                          # 도메인별 서비스 그룹화
│       ├── UserService.java           # 사용자 서비스 인터페이스
│       ├── UserServiceImpl.java      # 사용자 서비스 구현체
│       └── UserValidationService.java # 사용자 검증 서비스
│
├── 📁 model/                          # 3. Domain Layer
│   └── user/                          # 도메인별 엔티티 그룹화
│       ├── User.java                  # 사용자 엔티티
│       └── UserRole.java             # 사용자 역할 Enum
│
├── 📁 repository/                     # 4. Infrastructure/Data Access Layer
│   └── user/                          # 도메인별 리포지토리 그룹화
│       └── UserRepository.java        # 사용자 데이터 접근
│
├── 📁 mapper/                         # 4. Infrastructure/Data Access Layer
│   └── user/                          # 도메인별 매퍼 그룹화
│       └── UserMapper.java            # Entity-DTO 변환 (MapStruct)
│
├── 📁 security/                       # 4. Infrastructure/Data Access Layer
│   ├── jwt/                           # JWT 관련 인프라
│   │   ├── JwtTokenManager.java       # JWT 토큰 생성/검증
│   │   ├── JwtTokenService.java       # JWT 토큰 서비스
│   │   ├── JwtAuthenticationFilter.java # JWT 인증 필터
│   │   ├── JwtAuthenticationEntryPoint.java # 인증 실패 처리
│   │   └── JwtProperties.java         # JWT 설정
│   ├── service/
│   │   └── UserDetailsServiceImpl.java # Spring Security 사용자 정보 로드
│   └── utils/
│       └── SecurityConstants.java     # 보안 관련 상수
│
├── 📁 configuration/                  # 4. Infrastructure/Data Access Layer
│   ├── SecurityConfiguration.java     # Spring Security 설정
│   ├── MessageConfiguration.java      # 다국어 메시지 설정
│   ├── PasswordEncoderConfiguration.java # 비밀번호 인코더 설정
│   └── SwaggerConfiguration.java      # Swagger/OpenAPI 설정
│
└── 📁 utils/                          # 4. Infrastructure/Data Access Layer
    ├── ExceptionMessageAccessor.java   # 예외 메시지 접근 유틸리티
    ├── GeneralMessageAccessor.java    # 일반 메시지 접근 유틸리티
    └── ProjectConstants.java          # 프로젝트 상수
```

### 계층별 책임 및 역할

#### 1. Presentation Layer (표현 계층)
- **책임**: HTTP 요청/응답 처리, 사용자 인터페이스 제공
- **주요 구성요소**:
  - `controller/`: REST API 엔드포인트 정의 및 요청 라우팅
  - `dto/`: 클라이언트와 서버 간 데이터 전송 객체 (요청/응답)
  - `exceptions/`: 전역 예외 처리 및 에러 응답 변환
- **의존성**: Application/Business Layer에만 의존
- **원칙**: 
  - Entity를 직접 노출하지 않고 DTO 사용
  - 비즈니스 로직 포함 금지
  - 입력 유효성 검증 수행

#### 2. Application/Business Layer (애플리케이션/비즈니스 계층)
- **책임**: 비즈니스 로직 구현, 트랜잭션 관리, 도메인 서비스 조합
- **주요 구성요소**:
  - `service/`: 비즈니스 로직 구현 및 트랜잭션 관리
- **의존성**: Domain Layer와 Infrastructure Layer에 의존
- **원칙**:
  - 비즈니스 규칙 및 로직 구현
  - 트랜잭션 경계 정의
  - 여러 도메인 서비스 조합

#### 3. Domain Layer (도메인 계층)
- **책임**: 도메인 모델 정의, 비즈니스 규칙 표현
- **주요 구성요소**:
  - `model/`: 도메인 엔티티 및 값 객체
- **의존성**: 다른 계층에 의존하지 않음 (순수 도메인 모델)
- **원칙**:
  - 순수한 도메인 로직만 포함
  - 인프라스트럭처에 대한 의존성 없음
  - 재사용 가능한 도메인 모델

#### 4. Infrastructure/Data Access Layer (인프라/데이터 접근 계층)
- **책임**: 데이터 접근, 외부 시스템 연동, 기술적 세부사항 처리
- **주요 구성요소**:
  - `repository/`: 데이터베이스 접근 (JPA Repository)
  - `mapper/`: Entity-DTO 변환 (MapStruct)
  - `security/`: 보안 인프라 (JWT, Spring Security)
  - `configuration/`: Spring 설정 (Bean 정의)
  - `utils/`: 공통 유틸리티
- **의존성**: Domain Layer에 의존
- **원칙**:
  - 기술적 세부사항 캡슐화
  - 도메인 계층의 추상화 구현
  - 외부 시스템 연동 처리

### 의존성 방향 (Dependency Direction)

```
Controller → Service → Repository
    ↓           ↓          ↓
   DTO      Domain    Domain
    ↓           ↓          ↓
  Mapper ← Infrastructure
```

**핵심 원칙**:
- ✅ **단방향 의존성**: 상위 계층만 하위 계층에 의존
- ✅ **Domain Layer 순수성**: Domain Layer는 다른 계층에 의존하지 않음
- ✅ **인터페이스 활용**: Service는 인터페이스로 정의하여 결합도 감소
- ✅ **DTO 패턴**: Entity를 Presentation Layer에 직접 노출하지 않음

### 디자인 패턴 및 주요 설계

*   **4-Layered Architecture**: 계층별 명확한 책임 분리와 단방향 의존성 유지
*   **Controller-Service-Repository 패턴**: 각 계층의 역할을 명확히 분리하여 결합도를 낮춤
*   **DTO (Data Transfer Object) 패턴**: API 요청/응답 시 Entity를 직접 노출하지 않고 `Request/Response DTO`를 사용하여 보안과 안정성 향상
*   **Mapper 패턴**: `MapStruct`를 사용하여 Entity와 DTO 간의 변환 로직을 자동화하고, 보일러플레이트 코드 최소화
*   **Global Exception Handling**: `@ControllerAdvice`를 활용하여 애플리케이션 전역에서 발생하는 예외를 일관된 포맷으로 클라이언트에게 반환
*   **도메인별 패키지 구조**: Controller, Service, Repository, Model, Mapper, DTO를 도메인별로 그룹화하여 확장성과 유지보수성 향상

## 🛠 기술 스택

- **Java**: JDK 21
- **Framework**: Spring Boot 3.2.1
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Security**: Spring Security + JWT (Auth0)
- **Mapping**: MapStruct 1.6.3
- **Utils**: Lombok, Apache Commons Lang3
- **Documentation**: SpringDoc OpenAPI 3.0 (Swagger)

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
    java -jar build/libs/spring-boot-boilerplate-0.0.2-SNAPSHOT.jar
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
