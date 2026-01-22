package com.farukgenc.boilerplate.springboot.security.service;

import com.farukgenc.boilerplate.springboot.model.user.UserRole;
import com.farukgenc.boilerplate.springboot.dto.user.AuthenticatedUserDto;
import com.farukgenc.boilerplate.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * Spring Security 사용자 정보 서비스 구현체
 * 
 * <p>Spring Security의 UserDetailsService를 구현하여 사용자 인증 정보를 제공합니다.
 * 사용자명을 기준으로 사용자 정보를 조회하고, Spring Security가 사용할 수 있는 UserDetails 객체로 변환합니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/** 사용자명 또는 비밀번호가 잘못되었을 때 사용하는 메시지 */
	private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	private final UserService userService;

	/**
	 * 사용자명으로 사용자 정보 로드
	 * 
	 * <p>Spring Security 인증 과정에서 호출됩니다.
	 * 사용자명을 기준으로 사용자를 조회하고, UserDetails 객체로 변환하여 반환합니다.
	 * 
	 * @param username 사용자명
	 * @return UserDetails 객체
	 * @throws UsernameNotFoundException 사용자를 찾을 수 없는 경우
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {

		final AuthenticatedUserDto authenticatedUser = userService.findAuthenticatedUserByUsername(username);

		if (Objects.isNull(authenticatedUser)) {
			throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
		}

		final String authenticatedUsername = authenticatedUser.getUsername();
		final String authenticatedPassword = authenticatedUser.getPassword();
		final UserRole userRole = authenticatedUser.getUserRole();
		final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.name());

		return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
	}
}
