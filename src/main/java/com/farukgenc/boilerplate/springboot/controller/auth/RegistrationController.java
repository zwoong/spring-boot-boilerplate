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
	 * @param registrationRequest 회원가입 요청 정보
	 * @return 회원가입 응답 (HTTP 201 Created)
	 */
	@PostMapping
	@Operation(tags = "회원가입 서비스", description = "적절한 형식의 정보를 전송하여 시스템에 회원가입할 수 있습니다.")
	public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

		final RegistrationResponse registrationResponse = userService.registration(registrationRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}

}
