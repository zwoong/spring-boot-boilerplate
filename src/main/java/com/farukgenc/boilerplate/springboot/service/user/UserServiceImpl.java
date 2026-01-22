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

	/**
	 * 회원가입 성공 메시지 키
	 * 다국어 메시지 파일에서 사용되는 메시지 키
	 */
	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	/**
	 * 사용자 데이터 접근 리포지토리
	 * 데이터베이스에서 사용자 정보를 조회하고 저장하는 데 사용됩니다.
	 */
	private final UserRepository userRepository;

	/**
	 * BCrypt 비밀번호 인코더
	 * 사용자 비밀번호를 안전하게 암호화하기 위해 사용됩니다.
	 * BCrypt는 단방향 해시 알고리즘으로, 원본 비밀번호를 복원할 수 없습니다.
	 */
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 사용자 유효성 검증 서비스
	 * 회원가입 시 사용자명 및 이메일 중복 여부를 검증합니다.
	 */
	private final UserValidationService userValidationService;

	/**
	 * 일반 메시지 접근 유틸리티
	 * 다국어 메시지를 가져오기 위해 사용됩니다.
	 */
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
	 * <p>새로운 사용자를 시스템에 등록합니다. 다음 단계를 수행합니다:
	 * <ol>
	 *   <li>사용자 유효성 검증 (사용자명, 이메일 중복 확인)</li>
	 *   <li>RegistrationRequest를 User 엔티티로 변환</li>
	 *   <li>비밀번호 BCrypt 암호화</li>
	 *   <li>기본 사용자 역할(USER) 설정</li>
	 *   <li>데이터베이스에 사용자 저장</li>
	 *   <li>다국어 성공 메시지 생성 및 반환</li>
	 * </ol>
	 * 
	 * <p>트랜잭션: 이 메서드는 @Transactional이 없지만, Spring의 기본 동작에 따라
	 * Service 계층의 메서드는 자동으로 트랜잭션이 적용됩니다.
	 * 
	 * @param registrationRequest 회원가입 요청 정보 (이름, 이메일, 사용자명, 비밀번호)
	 * @return 회원가입 응답 (성공 메시지 포함)
	 * @throws com.farukgenc.boilerplate.springboot.exceptions.RegistrationException 
	 *         사용자명 또는 이메일이 이미 존재하는 경우
	 */
	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		// 사용자 유효성 검증 (사용자명, 이메일 중복 확인)
		userValidationService.validateUser(registrationRequest);

		// RegistrationRequest를 User 엔티티로 변환
		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		
		// 비밀번호 BCrypt 암호화 (단방향 해시, 복원 불가능)
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		// 기본 사용자 역할 설정 (일반 사용자)
		user.setUserRole(UserRole.USER);

		// 데이터베이스에 사용자 저장
		userRepository.save(user);

		// 다국어 성공 메시지 생성
		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		// 로그 기록
		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	/**
	 * 사용자명으로 인증된 사용자 정보 조회
	 * 
	 * <p>사용자명을 기준으로 사용자를 조회한 후, 인증에 필요한 정보만 포함된 DTO로 변환하여 반환합니다.
	 * 이 메서드는 주로 Spring Security의 인증 과정에서 사용됩니다.
	 * 
	 * @param username 조회할 사용자명
	 * @return 인증된 사용자 DTO (사용자명, 비밀번호, 역할 포함, 없으면 null)
	 */
	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		// 사용자명으로 사용자 조회
		final User user = findByUsername(username);

		// User 엔티티를 AuthenticatedUserDto로 변환 (인증에 필요한 정보만 포함)
		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}
