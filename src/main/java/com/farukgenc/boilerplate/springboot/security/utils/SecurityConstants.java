package com.farukgenc.boilerplate.springboot.security.utils;

/**
 * 보안 관련 상수 클래스
 * 
 * <p>JWT 토큰 처리에 사용되는 상수들을 정의합니다.
 * 유틸리티 클래스이므로 인스턴스 생성을 방지합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
public class SecurityConstants {

	/** JWT 토큰 접두사 (Bearer) */
	public static final String TOKEN_PREFIX = "Bearer ";

	/** Authorization 헤더 이름 */
	public static final String HEADER_STRING = "Authorization";

	/** 인스턴스 생성 방지 */
	private SecurityConstants() {

		throw new UnsupportedOperationException();
	}

}
