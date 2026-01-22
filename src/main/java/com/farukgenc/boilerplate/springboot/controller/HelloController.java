package com.farukgenc.boilerplate.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello 컨트롤러
 * 
 * <p>애플리케이션의 기본 상태를 확인하기 위한 간단한 테스트 엔드포인트를 제공합니다.
 * 인증이 필요한 엔드포인트로, JWT 토큰이 유효한지 확인하는 용도로 사용할 수 있습니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@RestController
public class HelloController {

	/**
	 * Hello 메시지 반환
	 * 
	 * <p>인증된 사용자에게 간단한 인사 메시지를 반환합니다.
	 * 이 엔드포인트는 JWT 토큰 인증이 필요하므로, 유효한 토큰을 헤더에 포함해야 합니다.
	 * 
	 * <p>사용 사례:
	 * <ul>
	 *   <li>애플리케이션 상태 확인</li>
	 *   <li>JWT 토큰 유효성 검증</li>
	 *   <li>인증된 사용자 접근 테스트</li>
	 * </ul>
	 * 
	 * @return HTTP 200 OK와 함께 "Hello Spring Boot Boilerplate" 메시지 반환
	 */
	@GetMapping("/hello")
	@Operation(tags = "Hello Service", description = "헤더에 토큰 정보를 전송하면 Hello 메시지를 반환합니다.")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello Spring Boot Boilerplate");
	}

}
