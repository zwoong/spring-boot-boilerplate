package com.farukgenc.boilerplate.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 비밀번호 암호화 설정 클래스
 * 
 * <p>이 클래스는 Spring Security의 비밀번호 암호화를 위한 PasswordEncoder Bean을 제공합니다.
 * BCrypt 해시 알고리즘을 사용하여 사용자 비밀번호를 안전하게 암호화합니다.
 * 
 * <p>BCrypt의 특징:
 * <ul>
 *   <li>단방향 해시 함수: 암호화된 비밀번호는 복호화할 수 없음</li>
 *   <li>Salt 자동 생성: 매번 다른 Salt를 사용하여 동일한 비밀번호도 다른 해시값 생성</li>
 *   <li>비용 계수(Strength): 해시 생성 비용을 조절하여 무차별 대입 공격 방어</li>
 *   <li>기본 비용 계수: 10 (2^10 = 1024번 반복)</li>
 * </ul>
 * 
 * <p>사용 예시:
 * <pre>{@code
 * @Autowired
 * private BCryptPasswordEncoder passwordEncoder;
 * 
 * // 비밀번호 암호화
 * String encodedPassword = passwordEncoder.encode("plainPassword");
 * 
 * // 비밀번호 검증
 * boolean matches = passwordEncoder.matches("plainPassword", encodedPassword);
 * }</pre>
 * 
 * @author Faruk
 * @since 2023년 3월
 */
@Configuration
public class PasswordEncoderConfiguration {

	/**
	 * BCryptPasswordEncoder Bean 생성
	 * 
	 * <p>BCrypt 알고리즘을 사용하는 PasswordEncoder 인스턴스를 생성합니다.
	 * 이 Bean은 Spring Security의 인증 과정에서 비밀번호 암호화 및 검증에 사용됩니다.
	 * 
	 * <p>기본 설정:
	 * <ul>
	 *   <li>비용 계수(Strength): 10 (기본값)</li>
	 *   <li>해시 길이: 60자</li>
	 *   <li>형식: $2a$[cost]$[22자 salt][31자 hash]</li>
	 * </ul>
	 * 
	 * <p>비용 계수 조정이 필요한 경우:
	 * <pre>{@code
	 * return new BCryptPasswordEncoder(12); // 비용 계수 12로 증가 (더 강력하지만 느림)
	 * }</pre>
	 * 
	 * @return BCryptPasswordEncoder 인스턴스
	 */
	@Bean
	public BCryptPasswordEncoder encoder() {
		// BCryptPasswordEncoder 생성 (기본 비용 계수 10 사용)
		// 비용 계수를 높이면 보안성은 증가하지만 해시 생성 시간도 증가함
		return new BCryptPasswordEncoder();
	}

}
