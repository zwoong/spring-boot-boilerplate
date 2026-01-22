package com.farukgenc.boilerplate.springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * API 예외 응답 DTO
 * 
 * <p>예외 발생 시 클라이언트에게 반환하는 표준 응답 형식입니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponse {

	/** 예외 메시지 */
	private String message;

	/** HTTP 상태 코드 */
	private HttpStatus status;

	/** 예외 발생 시간 */
	private LocalDateTime time;

}
