package com.farukgenc.boilerplate.springboot.exceptions;

import com.farukgenc.boilerplate.springboot.controller.LoginController;
import com.farukgenc.boilerplate.springboot.controller.RegistrationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * 인증 관련 예외 처리 ControllerAdvice
 * 
 * <p>로그인 및 회원가입 관련 예외를 통합하여 처리합니다.
 * LoginController와 RegistrationController에서 발생하는 예외를 처리합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@RestControllerAdvice(basePackageClasses = {LoginController.class, RegistrationController.class})
public class AuthControllerAdvice {

	/**
	 * 로그인 실패 예외 처리
	 * 
	 * <p>잘못된 자격 증명(BadCredentialsException)이 발생했을 때 처리합니다.
	 * 일반적으로 사용자명 또는 비밀번호가 일치하지 않을 때 발생합니다.
	 * 
	 * @param exception 발생한 BadCredentialsException
	 * @return HTTP 401 Unauthorized 응답
	 */
	@ExceptionHandler(BadCredentialsException.class)
	ResponseEntity<ApiExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {

		final ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/**
	 * 회원가입 예외 처리
	 * 
	 * <p>회원가입 과정에서 발생하는 예외(RegistrationException)를 처리합니다.
	 * 일반적으로 사용자명 또는 이메일 중복 시 발생합니다.
	 * 
	 * @param exception 발생한 RegistrationException
	 * @return HTTP 400 Bad Request 응답
	 */
	@ExceptionHandler(RegistrationException.class)
	ResponseEntity<ApiExceptionResponse> handleRegistrationException(RegistrationException exception) {

		final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
