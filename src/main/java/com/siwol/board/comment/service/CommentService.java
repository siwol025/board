package com.siwol.board.comment.service;

import com.siwol.board.comment.domain.CommentRepository;
import com.siwol.board.comment.domain.entity.Comment;
import com.siwol.board.comment.dto.request.CommentRequestDto;
import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.post.domain.repository.PostRepository;
import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.domain.repository.UserRepository;
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
        Post post = findByPostId(commentRequestDto.getPostId());
        User user = findByUserId(commentRequestDto.getUserId());
        Comment comment = commentRequestDto.toComment(post, user);
        commentRepository.save(comment);
    }

    private Post findByPostId(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
    }

    private User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }
}
