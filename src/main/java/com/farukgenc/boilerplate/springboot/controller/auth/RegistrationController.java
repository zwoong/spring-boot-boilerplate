package com.farukgenc.boilerplate.springboot.controller.auth;

import com.farukgenc.boilerplate.springboot.dto.auth.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.dto.auth.response.RegistrationResponse;
import com.farukgenc.boilerplate.springboot.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 회원가입 컨트롤러
 * 
 * <p>사용자 회원가입을 처리하는 REST API 엔드포인트를 제공합니다.
 *
 * @author Faruk
 * @since 2020년 8월
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

	private final UserService userService;

	/**
	 * 회원가입 요청 처리
	 * 
	 * <p>새로운 사용자를 시스템에 등록합니다.
	 * 요청 데이터는 유효성 검증을 거친 후 처리됩니다.
	 * 
	 * @param registrationRequest 회원가입 요청 정보 (이름, 이메일, 사용자명, 비밀번호)
	 * @return 회원가입 응답 (생성된 사용자 정보)
	 */
	@PostMapping
	@Operation(tags = "Registration Service", description = "적절한 형식의 정보를 전송하여 시스템에 회원가입할 수 있습니다.")
	public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {
		// 회원가입 처리
		final RegistrationResponse registrationResponse = userService.registration(registrationRequest);
		// HTTP 201 Created 상태 코드와 함께 응답 반환
		return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}

}
