package com.farukgenc.boilerplate.springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 유효성 검증 에러 응답 DTO
 * 
 * <p>Bean Validation 실패 시 클라이언트에게 반환하는 응답 형식입니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

	/** HTTP 상태 코드 */
	private HttpStatus status;

	/** 에러 발생 시간 */
	private LocalDateTime time;

	/** 유효성 검증 에러 메시지 목록 */
	private List<String> message;

}
