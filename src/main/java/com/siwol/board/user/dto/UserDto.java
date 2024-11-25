package com.siwol.board.user.dto;

import com.siwol.board.user.domain.entitiy.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long userId;
    private String username;

    @Builder
    public UserDto(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public static UserDto of(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}
