package com.blogapp.com.userservice.dtoMapper;

import com.blogapp.com.userservice.dto.UserDto;
import com.blogapp.com.userservice.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto UserToUserDto(User user);

    @InheritInverseConfiguration
    User UserDtoToUSer(UserDto userDto);
}
