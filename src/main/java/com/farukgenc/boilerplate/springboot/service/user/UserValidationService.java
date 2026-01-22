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

	/**
	 * 이메일 중복 예외 메시지 키
	 * 다국어 메시지 파일에서 사용되는 메시지 키
	 */
	private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

	/**
	 * 사용자명 중복 예외 메시지 키
	 * 다국어 메시지 파일에서 사용되는 메시지 키
	 */
	private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

	/**
	 * 사용자 데이터 접근 리포지토리
	 * 데이터베이스에서 사용자명 및 이메일 중복 여부를 확인하는 데 사용됩니다.
	 */
	private final UserRepository userRepository;

	/**
	 * 예외 메시지 접근 유틸리티
	 * 다국어 예외 메시지를 가져오기 위해 사용됩니다.
	 */
	private final ExceptionMessageAccessor exceptionMessageAccessor;

	/**
	 * 사용자 정보 유효성 검증
	 * 
	 * <p>회원가입 요청 정보의 유효성을 검증합니다.
	 * 사용자명과 이메일의 중복 여부를 확인하며, 중복이 발견되면 예외를 발생시킵니다.
	 * 
	 * <p>검증 순서:
	 * <ol>
	 *   <li>이메일 중복 확인</li>
	 *   <li>사용자명 중복 확인</li>
	 * </ol>
	 * 
	 * <p>검증이 성공하면 아무것도 반환하지 않으며, 실패하면 RegistrationException이 발생합니다.
	 * 
	 * @param registrationRequest 검증할 회원가입 요청 정보
	 * @throws RegistrationException 사용자명 또는 이메일이 이미 존재하는 경우
	 */
	public void validateUser(RegistrationRequest registrationRequest) {

		final String email = registrationRequest.getEmail();
		final String username = registrationRequest.getUsername();

		// 이메일 중복 확인
		checkEmail(email);
		// 사용자명 중복 확인
		checkUsername(username);
	}

	/**
	 * 사용자명 중복 확인
	 * 
	 * <p>데이터베이스에서 해당 사용자명이 이미 존재하는지 확인합니다.
	 * 중복이 발견되면 다국어 예외 메시지와 함께 RegistrationException을 발생시킵니다.
	 * 
	 * @param username 확인할 사용자명
	 * @throws RegistrationException 사용자명이 이미 존재하는 경우
	 */
	private void checkUsername(String username) {

		// 데이터베이스에서 사용자명 존재 여부 확인
		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			// 중복 발견 시 경고 로그 기록
			log.warn("Username: {} already being used!", username);

			// 다국어 예외 메시지 가져오기
			final String existsUsername = exceptionMessageAccessor.getMessage(null, USERNAME_ALREADY_EXISTS);
			
			// 예외 발생
			throw new RegistrationException(existsUsername);
		}

	}

	/**
	 * 이메일 중복 확인
	 * 
	 * <p>데이터베이스에서 해당 이메일이 이미 존재하는지 확인합니다.
	 * 중복이 발견되면 다국어 예외 메시지와 함께 RegistrationException을 발생시킵니다.
	 * 
	 * @param email 확인할 이메일 주소
	 * @throws RegistrationException 이메일이 이미 존재하는 경우
	 */
	private void checkEmail(String email) {

		// 데이터베이스에서 이메일 존재 여부 확인
		final boolean existsByEmail = userRepository.existsByEmail(email);

		if (existsByEmail) {

			// 중복 발견 시 경고 로그 기록
			log.warn("Email: {} already being used!", email);

			// 다국어 예외 메시지 가져오기
			final String existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
			
			// 예외 발생
			throw new RegistrationException(existsEmail);
		}
	}

}
