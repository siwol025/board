package com.siwol.board.user.dto;

import com.siwol.board.user.domain.entitiy.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private String username;

    @Builder
    public UserDto(String username) {
        this.username = username;
    }

    public static UserDto of(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }
}
