#!/bin/bash

# Spring Boot 애플리케이션 및 PostgreSQL 데이터베이스 시작 스크립트 (개발 모드)
# 사용법: ./run.sh

echo "🚀 서버를 시작합니다... (개발 모드)"
echo "📝 소스코드 변경 시 자동으로 재시작됩니다."

# Docker Compose를 사용하여 서비스 시작 (빌드 포함, 백그라운드 실행)
docker compose up --build -d

# 서비스 상태 확인
echo ""
echo "📊 서비스 상태:"
docker compose ps

echo ""
echo "✅ 서버가 시작되었습니다!"
echo "📝 로그 확인: docker compose logs -f"
echo "🛑 서버 중지: docker compose down"
