package com.siwol.board.comment.dto.response;

import com.siwol.board.comment.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String author;

    public static CommentResponseDto of(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getComment(), comment.getAuthor().getUsername());
    }
}
