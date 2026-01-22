package com.farukgenc.boilerplate.springboot.service.user;

import com.farukgenc.boilerplate.springboot.model.user.User;
import com.farukgenc.boilerplate.springboot.dto.auth.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.dto.auth.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.dto.user.AuthenticatedUserDto;

/**
 * 사용자 서비스 인터페이스
 * 
 * <p>사용자 관련 비즈니스 로직을 처리하는 서비스의 계약을 정의합니다.
 * 인터페이스를 사용하여 구현체와의 결합도를 낮추고, 테스트 용이성을 높입니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
public interface UserService {

	/**
	 * 사용자명으로 사용자 조회
	 * 
	 * @param username 사용자명
	 * @return 사용자 엔티티 (없으면 null)
	 */
	User findByUsername(String username);

	/**
	 * 회원가입 처리
	 * 
	 * @param registrationRequest 회원가입 요청 정보
	 * @return 회원가입 응답 (성공 메시지 포함)
	 */
	RegistrationResponse registration(RegistrationRequest registrationRequest);

	/**
	 * 사용자명으로 인증된 사용자 정보 조회
	 * 
	 * @param username 사용자명
	 * @return 인증된 사용자 DTO (없으면 null)
	 */
	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
