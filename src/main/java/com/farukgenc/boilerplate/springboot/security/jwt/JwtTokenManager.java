package com.farukgenc.boilerplate.springboot.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.farukgenc.boilerplate.springboot.model.user.User;
import com.farukgenc.boilerplate.springboot.model.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT 토큰 관리자
 * 
 * <p>JWT 토큰의 생성, 검증, 파싱을 담당하는 컴포넌트입니다.
 * Auth0의 java-jwt 라이브러리를 사용하여 토큰을 처리합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Component
@RequiredArgsConstructor
public class JwtTokenManager {

	private final JwtProperties jwtProperties;

	/**
	 * JWT 토큰 생성
	 * 
	 * @param user 사용자 엔티티
	 * @return JWT 토큰 문자열
	 */
	public String generateToken(User user) {

		final String username = user.getUsername();
		final UserRole userRole = user.getUserRole();

		//@formatter:off
		return JWT.create()
				.withSubject(username)
				.withIssuer(jwtProperties.getIssuer())
				.withClaim("role", userRole.name())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMinute() * 60 * 1000))
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
		//@formatter:on
	}

	/**
	 * 토큰에서 사용자명 추출
	 * 
	 * @param token JWT 토큰
	 * @return 사용자명
	 */
	public String getUsernameFromToken(String token) {

		final DecodedJWT decodedJWT = getDecodedJWT(token);
		return decodedJWT.getSubject();
	}

	/**
	 * 토큰 유효성 검증
	 * 
	 * @param token JWT 토큰
	 * @param authenticatedUsername 인증된 사용자명
	 * @return 토큰이 유효하면 true
	 */
	public boolean validateToken(String token, String authenticatedUsername) {

		final String usernameFromToken = getUsernameFromToken(token);
		final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
		final boolean tokenExpired = isTokenExpired(token);

		return equalsUsername && !tokenExpired;
	}

	/**
	 * 토큰 만료 여부 확인
	 * 
	 * @param token JWT 토큰
	 * @return 만료되었으면 true
	 */
	private boolean isTokenExpired(String token) {

		final Date expirationDateFromToken = getExpirationDateFromToken(token);
		return expirationDateFromToken.before(new Date());
	}

	/**
	 * 토큰에서 만료 시간 추출
	 * 
	 * @param token JWT 토큰
	 * @return 만료 시간
	 */
	private Date getExpirationDateFromToken(String token) {

		final DecodedJWT decodedJWT = getDecodedJWT(token);
		return decodedJWT.getExpiresAt();
	}

	/**
	 * 토큰 디코딩 및 검증
	 * 
	 * @param token JWT 토큰
	 * @return 디코딩된 JWT
	 */
	private DecodedJWT getDecodedJWT(String token) {

		final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes())).build();
		return jwtVerifier.verify(token);
	}

}
