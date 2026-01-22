package com.farukgenc.boilerplate.springboot.mapper.user;

import com.farukgenc.boilerplate.springboot.model.user.User;
import com.farukgenc.boilerplate.springboot.dto.auth.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.dto.user.AuthenticatedUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 사용자 매퍼 인터페이스
 * 
 * <p>MapStruct를 사용하여 User 엔티티와 DTO 간의 변환을 수행합니다.
 * 컴파일 타임에 구현체가 자동 생성됩니다.
 * 
 * @author Faruk
 * @since 2020년 8월
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	/** 매퍼 인스턴스 (싱글톤) */
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	/**
	 * RegistrationRequest를 User 엔티티로 변환
	 * 
	 * @param registrationRequest 회원가입 요청 DTO
	 * @return User 엔티티
	 */
	User convertToUser(RegistrationRequest registrationRequest);

	/**
	 * User 엔티티를 AuthenticatedUserDto로 변환
	 * 
	 * @param user User 엔티티
	 * @return AuthenticatedUserDto
	 */
	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	/**
	 * AuthenticatedUserDto를 User 엔티티로 변환
	 * 
	 * @param authenticatedUserDto 인증된 사용자 DTO
	 * @return User 엔티티
	 */
	User convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
