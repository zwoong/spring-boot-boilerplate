package com.farukgenc.boilerplate.springboot.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원가입 요청 DTO
 * 
 * <p>회원가입 API에서 사용되는 요청 데이터 전송 객체입니다.
 * Bean Validation을 사용하여 필드 유효성 검증을 수행합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Data
@NoArgsConstructor
public class RegistrationRequest {

	/** 사용자 이름 (필수) */
	@NotEmpty(message = "{registration_name_not_empty}")
	private String name;

	/** 이메일 주소 (필수, 유효한 형식) */
	@Email(message = "{registration_email_is_not_valid}")
	@NotEmpty(message = "{registration_email_not_empty}")
	private String email;

	/** 사용자명 (필수) */
	@NotEmpty(message = "{registration_username_not_empty}")
	private String username;

	/** 비밀번호 (필수) */
	@NotEmpty(message = "{registration_password_not_empty}")
	private String password;

}
