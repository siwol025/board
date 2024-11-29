package com.siwol.board.comment.service;

import com.siwol.board.comment.domain.CommentRepository;
import com.siwol.board.comment.domain.entity.Comment;
import com.siwol.board.comment.dto.request.CommentRequestDto;
import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.post.domain.repository.PostRepository;
import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.domain.repository.UserRepository;
import com.siwol.board.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addComment(CommentRequestDto commentRequestDto) {
        Post post = findPostByPostId(commentRequestDto.getPostId());
        User user = findUserByUserId(commentRequestDto.getUserId());
        Comment comment = commentRequestDto.toComment(post, user);
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, UserDto loginUser) {
        Comment comment = findCommentByCommentId(commentId);
        checkCommentedUser(comment, loginUser);
        commentRepository.delete(comment);
    }

    private Comment findCommentByCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
    }

    private Post findPostByPostId(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
    }

    private User findUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }
    
    private void checkCommentedUser(Comment comment,UserDto user) {
        if (!comment.isCommentedBy(user)) {
            throw new IllegalArgumentException("해당 댓글의 작성자가 아닙니다.");
        }
    }
}
