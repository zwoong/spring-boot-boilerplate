#!/bin/bash

# Spring Boot 애플리케이션 및 PostgreSQL 데이터베이스 시작 스크립트
# 사용법:
#   ./run.sh          # 개발 모드로 실행 (기본값)
#   ./run.sh dev      # 개발 모드로 실행
#   ./run.sh prod     # 운영 모드로 실행

# 모드 설정 (기본값: dev)
MODE=${1:-dev}

# Docker Compose 파일 선택
if [ "$MODE" = "prod" ]; then
    COMPOSE_FILE="docker-compose.prod.yml"
    MODE_NAME="운영 모드"
    echo "🚀 서버를 운영 모드로 시작합니다..."
else
    COMPOSE_FILE="docker-compose.yml"
    MODE_NAME="개발 모드"
    echo "🚀 서버를 개발 모드로 시작합니다..."
    echo "📝 소스코드 변경 시 자동으로 재시작됩니다."
fi

# Docker Compose를 사용하여 서비스 시작 (빌드 포함, 백그라운드 실행)
docker compose -f "$COMPOSE_FILE" up --build -d

# 서비스 상태 확인
echo ""
echo "📊 서비스 상태 ($MODE_NAME):"
docker compose -f "$COMPOSE_FILE" ps

echo ""
echo "✅ 서버가 시작되었습니다! ($MODE_NAME)"
echo "📝 로그 확인: docker compose -f $COMPOSE_FILE logs -f"
echo "🛑 서버 중지: docker compose -f $COMPOSE_FILE down"
