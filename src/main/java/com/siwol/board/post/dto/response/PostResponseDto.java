package com.siwol.board.post.dto.response;

import com.siwol.board.post.domain.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;

    public static PostResponseDto of (Post post) {
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContents());
    }
}
