package com.farukgenc.boilerplate.springboot.service.user;

import com.farukgenc.boilerplate.springboot.model.user.User;
import com.farukgenc.boilerplate.springboot.model.user.UserRole;
import com.farukgenc.boilerplate.springboot.repository.user.UserRepository;
import com.farukgenc.boilerplate.springboot.dto.auth.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.dto.auth.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.dto.user.AuthenticatedUserDto;
import com.farukgenc.boilerplate.springboot.mapper.user.UserMapper;
import com.farukgenc.boilerplate.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 사용자 서비스 구현체
 * 
 * <p>사용자 관련 비즈니스 로직을 구현하는 서비스 클래스입니다.
 * UserService 인터페이스를 구현하며, 사용자 조회, 회원가입, 인증된 사용자 정보 조회 등의 기능을 제공합니다.
 * 
 * <p>주요 기능:
 * <ul>
 *   <li>사용자명으로 사용자 조회</li>
 *   <li>회원가입 처리 (유효성 검증, 비밀번호 암호화, 사용자 저장)</li>
 *   <li>인증된 사용자 정보 조회 (DTO 변환)</li>
 * </ul>
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	/** 회원가입 성공 메시지 키 */
	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserValidationService userValidationService;
	private final GeneralMessageAccessor generalMessageAccessor;

	/**
	 * 사용자명으로 사용자 조회
	 * 
	 * <p>데이터베이스에서 사용자명을 기준으로 사용자 정보를 조회합니다.
	 * 
	 * @param username 조회할 사용자명
	 * @return 사용자 엔티티 (없으면 null)
	 */
	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	/**
	 * 회원가입 처리
	 * 
	 * @param registrationRequest 회원가입 요청 정보
	 * @return 회원가입 응답 (성공 메시지 포함)
	 * @throws RegistrationException 사용자명 또는 이메일이 이미 존재하는 경우
	 */
	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	/**
	 * 사용자명으로 인증된 사용자 정보 조회
	 * 
	 * @param username 조회할 사용자명
	 * @return 인증된 사용자 DTO (없으면 null)
	 */
	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);
		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}
