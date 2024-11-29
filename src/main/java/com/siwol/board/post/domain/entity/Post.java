package com.siwol.board.post.domain.entity;

import com.siwol.board.comment.domain.Comments;
import com.siwol.board.comment.dto.response.CommentResponseDto;
import com.siwol.board.post.dto.request.PostRequestDto;
import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.dto.UserDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Embedded
    private Comments comments;

    @Builder
    public Post(Long id, String title, String contents, User author) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.comments = new Comments();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

    public boolean isSameAuthor(UserDto user) {
        return author.isSameUser(user);
    }

    public List<CommentResponseDto> getCommentResponseDtoList() {
        return comments.toDtoList();
    }
}
