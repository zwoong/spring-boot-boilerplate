package com.farukgenc.boilerplate.springboot.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT 설정 프로퍼티
 * 
 * <p>application.yml에서 JWT 관련 설정을 바인딩하는 클래스입니다.
 * 
 * @author Faruk
 * @since 2022년 10월
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

	/** JWT 발급자 */
	private String issuer;

	/** JWT 서명에 사용할 비밀 키 */
	private String secretKey;

	/** 토큰 만료 시간 (분) */
	private long expirationMinute;

}
