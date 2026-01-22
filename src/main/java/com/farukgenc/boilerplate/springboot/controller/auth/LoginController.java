package com.farukgenc.boilerplate.springboot.controller.auth;

import com.farukgenc.boilerplate.springboot.dto.auth.request.LoginRequest;
import com.farukgenc.boilerplate.springboot.dto.auth.response.LoginResponse;
import com.farukgenc.boilerplate.springboot.security.jwt.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 로그인 컨트롤러
 * 
 * <p>사용자 로그인을 처리하는 REST API 엔드포인트를 제공합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

	private final JwtTokenService jwtTokenService;

	/**
	 * 로그인 요청 처리
	 * 
	 * @param loginRequest 로그인 요청 정보 (사용자명, 비밀번호)
	 * @return 로그인 응답 (JWT 토큰)
	 */
	@PostMapping
	@Operation(tags = "Login Service", description = "올바른 정보로 로그인하여 토큰 정보를 성공적으로 획득할 수 있습니다.")
	public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

		final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

		return ResponseEntity.ok(loginResponse);
	}

}
