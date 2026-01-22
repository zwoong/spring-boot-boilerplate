package com.farukgenc.boilerplate.springboot.dto.auth.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그인 요청 DTO
 * 
 * <p>로그인 API에서 사용되는 요청 데이터 전송 객체입니다.
 * Bean Validation을 사용하여 필드 유효성 검증을 수행합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Data
@NoArgsConstructor
public class LoginRequest {

	/** 사용자명 (필수) */
	@NotEmpty(message = "{login_username_not_empty}")
	private String username;

	/** 비밀번호 (필수) */
	@NotEmpty(message = "{login_password_not_empty}")
	private String password;

}
