package com.siwol.board.post.dto.response;

import com.siwol.board.post.domain.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Long authorId;
    private String authorUsername;

    public static PostDetailResponseDto of (Post post) {
        return new PostDetailResponseDto(post.getId(), post.getTitle(),
                post.getContents(), post.getAuthor().getId(), post.getAuthor().getUsername());
    }
}