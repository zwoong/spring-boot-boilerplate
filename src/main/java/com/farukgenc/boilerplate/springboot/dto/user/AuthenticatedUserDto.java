package com.farukgenc.boilerplate.springboot.dto.user;

import com.farukgenc.boilerplate.springboot.model.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 인증된 사용자 DTO
 * 
 * <p>인증된 사용자 정보를 전달하는 데이터 전송 객체입니다.
 * Spring Security 인증 과정에서 사용됩니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Getter
@Setter
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
