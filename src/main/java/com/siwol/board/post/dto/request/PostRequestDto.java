package com.siwol.board.post.dto.request;

import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRequestDto {
    private String title;
    private String contents;

    public Post toPost(User user) {
        return Post.builder()
                .title(title)
                .contents(contents)
                .author(user)
                .build();
    }

    @Builder
    public PostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
