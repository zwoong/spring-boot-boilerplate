package com.farukgenc.boilerplate.springboot.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWT 인증 진입점
 * 
 * <p>인증되지 않은 사용자가 보호된 리소스에 접근할 때 호출됩니다.
 * HTTP 401 Unauthorized 응답을 반환합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
