package com.siwol.board.post.service;

import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.post.dto.request.PostRequestDto;
import com.siwol.board.post.dto.response.PostResponseDto;
import com.siwol.board.post.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toBoard());
        return PostResponseDto.of(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::of).toList();
    }

    @Transactional(readOnly = true)
    public PostResponseDto findPostById(Long id) {
        return postRepository.findById(id).map(PostResponseDto::of).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    @Transactional
    public PostResponseDto updatePostById(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Post updatePost = post.update(postRequestDto);

        return PostResponseDto.of(updatePost);
    }

    @Transactional
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
