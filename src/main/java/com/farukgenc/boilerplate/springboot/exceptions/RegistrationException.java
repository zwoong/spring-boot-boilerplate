package com.farukgenc.boilerplate.springboot.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 회원가입 예외
 * 
 * <p>회원가입 과정에서 발생하는 비즈니스 예외입니다.
 * 주로 사용자명 또는 이메일 중복 시 발생합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	/** 예외 메시지 (다국어 지원) */
	private final String errorMessage;

}
