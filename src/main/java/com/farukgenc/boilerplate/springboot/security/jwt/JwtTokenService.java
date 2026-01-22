package com.farukgenc.boilerplate.springboot.security.jwt;

import com.farukgenc.boilerplate.springboot.mapper.user.UserMapper;
import com.farukgenc.boilerplate.springboot.service.user.UserService;
import com.farukgenc.boilerplate.springboot.model.user.User;
import com.farukgenc.boilerplate.springboot.dto.auth.request.LoginRequest;
import com.farukgenc.boilerplate.springboot.dto.auth.response.LoginResponse;
import com.farukgenc.boilerplate.springboot.dto.user.AuthenticatedUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * JWT 토큰 서비스
 * 
 * <p>로그인 요청을 처리하고 JWT 토큰을 생성하는 서비스입니다.
 * Spring Security의 AuthenticationManager를 사용하여 사용자 인증을 수행합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;
	private final JwtTokenManager jwtTokenManager;
	private final AuthenticationManager authenticationManager;

	/**
	 * 로그인 응답 생성
	 * 
	 * <p>사용자 인증 후 JWT 토큰을 생성하여 반환합니다.
	 * 
	 * @param loginRequest 로그인 요청 정보
	 * @return 로그인 응답 (JWT 토큰 포함)
	 */
	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
		final String token = jwtTokenManager.generateToken(user);

		log.info("{} has successfully logged in!", user.getUsername());

		return new LoginResponse(token);
	}

}
