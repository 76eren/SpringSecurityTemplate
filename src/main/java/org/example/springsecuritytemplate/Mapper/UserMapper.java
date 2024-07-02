package org.example.springsecuritytemplate.Mapper;


import org.example.springsecuritytemplate.Dto.User.UserResponseDTO;
import org.example.springsecuritytemplate.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserResponseDTO fromEntity(User user) {

        return UserResponseDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
