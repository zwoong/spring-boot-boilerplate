package com.farukgenc.boilerplate.springboot.dto.user;

import com.farukgenc.boilerplate.springboot.model.user.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 인증된 사용자 DTO
 * 
 * <p>인증된 사용자 정보를 전달하는 데이터 전송 객체입니다.
 * Spring Security 인증 과정에서 사용됩니다.
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
public class AuthenticatedUserDto {

	/**
	 * 이름
	 */
	private String name;

	/**
	 * 사용자명
	 */
	private String username;

	/**
	 * 비밀번호 (암호화됨)
	 */
	private String password;

	/**
	 * 사용자 역할
	 */
	private UserRole userRole;

}
