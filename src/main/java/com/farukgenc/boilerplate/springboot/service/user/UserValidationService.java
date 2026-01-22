package com.farukgenc.boilerplate.springboot.service.user;

import com.farukgenc.boilerplate.springboot.utils.ExceptionMessageAccessor;
import com.farukgenc.boilerplate.springboot.exceptions.RegistrationException;
import com.farukgenc.boilerplate.springboot.repository.user.UserRepository;
import com.farukgenc.boilerplate.springboot.dto.auth.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 사용자 유효성 검증 서비스
 * 
 * <p>회원가입 시 사용자 정보의 유효성을 검증하는 서비스 클래스입니다.
 * 주로 사용자명과 이메일의 중복 여부를 확인하여 데이터 무결성을 보장합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>사용자명 중복 검증</li>
 *   <li>이메일 중복 검증</li>
 *   <li>검증 실패 시 다국어 예외 메시지 제공</li>
 * </ul>
 * 
 * <p>이 서비스는 회원가입 프로세스의 일부로 사용되며,
 * 중복된 사용자명이나 이메일이 발견되면 RegistrationException을 발생시킵니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

	/** 이메일 중복 예외 메시지 키 */
	private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

	/** 사용자명 중복 예외 메시지 키 */
	private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

	private final UserRepository userRepository;
	private final ExceptionMessageAccessor exceptionMessageAccessor;

	/**
	 * 사용자 정보 유효성 검증
	 * 
	 * @param registrationRequest 검증할 회원가입 요청 정보
	 * @throws RegistrationException 사용자명 또는 이메일이 이미 존재하는 경우
	 */
	public void validateUser(RegistrationRequest registrationRequest) {

		final String email = registrationRequest.getEmail();
		final String username = registrationRequest.getUsername();

		checkEmail(email);
		checkUsername(username);
	}

	/**
	 * 사용자명 중복 확인
	 * 
	 * @param username 확인할 사용자명
	 * @throws RegistrationException 사용자명이 이미 존재하는 경우
	 */
	private void checkUsername(String username) {

		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			log.warn("Username: {} already being used!", username);

			final String existsUsername = exceptionMessageAccessor.getMessage(null, USERNAME_ALREADY_EXISTS);
			throw new RegistrationException(existsUsername);
		}

	}

	/**
	 * 이메일 중복 확인
	 * 
	 * @param email 확인할 이메일 주소
	 * @throws RegistrationException 이메일이 이미 존재하는 경우
	 */
	private void checkEmail(String email) {

		final boolean existsByEmail = userRepository.existsByEmail(email);

		if (existsByEmail) {

			log.warn("Email: {} already being used!", email);

			final String existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
			throw new RegistrationException(existsEmail);
		}
	}

}
