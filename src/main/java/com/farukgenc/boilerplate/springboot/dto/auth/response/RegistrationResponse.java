package com.farukgenc.boilerplate.springboot.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원가입 응답 DTO
 * 
 * <p>회원가입 API 응답 데이터 전송 객체입니다.
 * 회원가입 성공 메시지를 반환합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

	/** 회원가입 성공 메시지 (다국어 지원) */
	private String message;

}
