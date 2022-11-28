package com.kingsm01n.kpicursova.mapper;

import com.kingsm01n.kpicursova.dto.UserDto;
import com.kingsm01n.kpicursova.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper extends BaseMapper<UserDto, User> {

    @Mapping(target = "token", source = "token")
    UserDto entityToDto(User actor, String token);

}
