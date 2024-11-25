package com.siwol.board.post.service;

import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.post.dto.request.PostRequestDto;
import com.siwol.board.post.dto.response.PostDetailResponseDto;
import com.siwol.board.post.repository.PostRepository;
import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.dto.UserDto;
import com.siwol.board.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostDetailResponseDto createPost(PostRequestDto postRequestDto, UserDto currentUser) {
        User user = userRepository.findById(currentUser.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("찾을 수 없는 유저입니다."));

        Post post = postRepository.save(postRequestDto.toPost(user));

        return PostDetailResponseDto.of(post);
    }

    @Transactional(readOnly = true)
    public List<PostDetailResponseDto> findAllPosts() {
        return postRepository.findAll().stream().map(PostDetailResponseDto::of).toList();
    }

    @Transactional(readOnly = true)
    public PostDetailResponseDto getPostDetail(Long id) {
        return PostDetailResponseDto.of(findPostById(id));
    }


    @Transactional
    public PostDetailResponseDto updatePostById(Long id, PostRequestDto postRequestDto, UserDto currentUser) {
        Post post = findPostById(id);
        checkAuthor(post,currentUser);
        post.update(postRequestDto);

        return PostDetailResponseDto.of(post);
    }

    @Transactional
    public void deletePostById(Long id, UserDto currentUser) {
        checkAuthor(findPostById(id), currentUser);
        postRepository.deleteById(id);
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    private void checkAuthor(Post post, UserDto user) {
        if (!post.isSameAuthor(user)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
    }
}
