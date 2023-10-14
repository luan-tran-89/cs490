package com.edu.miu.mapper;

import com.edu.miu.dto.RegisterUserDto;
import com.edu.miu.dto.UserDto;
import com.edu.miu.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author gasieugru
 */
@Component
public class UserMapper extends Mapper<User, UserDto> {
    public UserMapper() {
        super(User.class, UserDto.class);
    }

    @Override
    public UserDto toDto(User t){
        if (t == null) {
            return null;
        }

        return modelMapper
                .typeMap(User.class, UserDto.class)
//                .addMappings(m -> m.skip(UserDto::setPassword))
                .map(t);
    }

    public User toEntity(RegisterUserDto userDto) {
        return modelMapper
                .typeMap(RegisterUserDto.class, User.class)
                .map(userDto);
    }

}
