package com.farukgenc.boilerplate.springboot.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원가입 응답 DTO
 * 
 * <p>회원가입 API 응답 데이터 전송 객체입니다.
 * 회원가입 성공 메시지를 반환합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

	/**
	 * 회원가입 성공 메시지
	 * 
	 * <p>다국어 지원 메시지 (예: "{username}님이 성공적으로 등록되었습니다!")
	 */
	private String message;

}
