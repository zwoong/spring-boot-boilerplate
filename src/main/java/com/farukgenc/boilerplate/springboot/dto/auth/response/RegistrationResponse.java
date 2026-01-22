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
@AllArgsConstructor
public class RegistrationResponse {

	/**
	 * 회원가입 성공 메시지
	 * 
	 * <p>다국어 지원 메시지 (예: "{username}님이 성공적으로 등록되었습니다!")
	 */
	private String message;

}
