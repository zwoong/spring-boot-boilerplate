package com.farukgenc.boilerplate.springboot.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 유효성 검증 예외 처리 ControllerAdvice
 * 
 * <p>Bean Validation 실패 시 발생하는 예외를 처리합니다.
 * 모든 컨트롤러에서 발생하는 유효성 검증 에러를 일관된 형식으로 반환합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Slf4j
@RestControllerAdvice
public class ValidationAdvice {

	/**
	 * 유효성 검증 예외 처리
	 * 
	 * <p>@Valid 어노테이션으로 검증된 요청 데이터가 유효하지 않을 때 발생하는 예외를 처리합니다.
	 * 
	 * @param exception 발생한 MethodArgumentNotValidException
	 * @return HTTP 400 Bad Request 응답
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

		final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		final List<String> errorList = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

		final ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), errorList);

		log.warn("Validation errors : {} , Parameters : {}", errorList, exception.getBindingResult().getTarget());

		return ResponseEntity.status(validationErrorResponse.getStatus()).body(validationErrorResponse);
	}

}
