package com.farukgenc.boilerplate.springboot.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 응답 DTO
 * 
 * <p>로그인 API 응답 데이터 전송 객체입니다.
 * JWT 토큰을 반환합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

	/**
	 * JWT 토큰
	 * 
	 * <p>인증에 사용할 JWT 토큰
	 */
	private String token;

}
