package com.farukgenc.boilerplate.springboot.mapper;

import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.dto.auth.request.RegistrationRequest;
import com.farukgenc.boilerplate.springboot.dto.user.AuthenticatedUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
