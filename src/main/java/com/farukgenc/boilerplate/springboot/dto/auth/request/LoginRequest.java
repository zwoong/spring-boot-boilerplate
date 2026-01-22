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
 * <p>@Data 어노테이션은 다음을 포함합니다:
 * <ul>
 *   <li>@Getter: 모든 필드에 대한 getter 메서드 생성</li>
 *   <li>@Setter: 모든 필드에 대한 setter 메서드 생성</li>
 *   <li>@ToString: toString() 메서드 생성</li>
 *   <li>@EqualsAndHashCode: equals()와 hashCode() 메서드 생성</li>
 *   <li>@RequiredArgsConstructor: final 필드에 대한 생성자 생성</li>
 * </ul>
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Data
@NoArgsConstructor
public class LoginRequest {

	/**
	 * 사용자명
	 * 
	 * <p>로그인에 사용할 사용자명 (필수 입력)
	 */
	@NotEmpty(message = "{login_username_not_empty}")
	private String username;

	/**
	 * 비밀번호
	 * 
	 * <p>로그인에 사용할 비밀번호 (필수 입력)
	 */
	@NotEmpty(message = "{login_password_not_empty}")
	private String password;

}
