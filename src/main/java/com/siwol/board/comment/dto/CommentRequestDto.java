package com.siwol.board.comment.dto;

import com.siwol.board.comment.domain.entity.Comment;
import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentRequestDto {
    private Long postId;
    private Long userId;
    private String comment;

    public Comment toComment(Post post, User user) {
        return Comment.builder()
                .comment(comment)
                .post(post)
                .author(user)
                .build();
    }

    public static CommentRequestDto of(Long postId, UserDto loginUser, CommentRequestDto request) {
        return new CommentRequestDto(postId, loginUser.getUserId(), request.comment);
    }
}
