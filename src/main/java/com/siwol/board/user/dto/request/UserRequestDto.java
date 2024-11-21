package com.siwol.board.user.dto.request;

import com.siwol.board.user.domain.entitiy.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {
    private String username;
    private String password;

    @Builder
    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User toUser() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
