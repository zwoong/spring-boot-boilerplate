package com.farukgenc.boilerplate.springboot.configuration;

import com.farukgenc.boilerplate.springboot.security.jwt.JwtAuthenticationEntryPoint;
import com.farukgenc.boilerplate.springboot.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 설정 클래스
 * 
 * <p>JWT 기반 인증을 사용하는 보안 설정을 구성합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final JwtAuthenticationEntryPoint unauthorizedHandler;

	/**
	 * AuthenticationManager Bean 생성
	 * 
	 * @param authenticationConfiguration 인증 설정
	 * @return AuthenticationManager 인스턴스
	 */
	@Bean
	public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/**
	 * SecurityFilterChain 설정
	 * 
	 * <p>보안 필터 체인을 구성하여 다음을 설정합니다:
	 * <ul>
	 *   <li>CSRF, CORS 비활성화 (JWT 사용 시 불필요)</li>
	 *   <li>JWT 인증 필터 추가</li>
	 *   <li>공개 엔드포인트: /register, /login, Swagger, Actuator</li>
	 *   <li>나머지 요청은 인증 필요</li>
	 *   <li>세션 사용 안 함 (STATELESS)</li>
	 *   <li>인증 실패 시 처리</li>
	 * </ul>
	 * 
	 * @param http HttpSecurity 객체
	 * @return SecurityFilterChain 인스턴스
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		//@formatter:off

		return http
				.csrf(CsrfConfigurer::disable)  // CSRF 비활성화 (JWT 사용 시 불필요)
				.cors(CorsConfigurer::disable)   // CORS 비활성화 (필요시 별도 설정)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // JWT 필터 추가
				.authorizeHttpRequests(request -> request.requestMatchers(
																		  "/register",      // 회원가입
																	      "/login",         // 로그인
																	      "/v3/api-docs/**",  // Swagger API 문서
																          "/swagger-ui/**",   // Swagger UI
																	      "/swagger-ui.html", // Swagger UI
																	      "/actuator/**")     // Actuator 엔드포인트
													   .permitAll()        // 위 경로는 인증 없이 접근 가능
													   .anyRequest()       // 나머지 모든 요청
													   .authenticated())   // 인증 필요
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 세션 사용 안 함
				.exceptionHandling(handler -> handler.authenticationEntryPoint(unauthorizedHandler))  // 인증 실패 시 처리
				.build();

		//@formatter:on
	}

}
