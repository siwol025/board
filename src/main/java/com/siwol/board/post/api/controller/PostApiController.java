package com.siwol.board.post.api.controller;

import com.siwol.board.post.dto.request.PostRequestDto;
import com.siwol.board.post.dto.response.PostDetailResponseDto;
import com.siwol.board.post.application.PostService;
import com.siwol.board.user.auth.LoginUser;
import com.siwol.board.user.dto.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class PostApiController {
    private final PostService boardService;

    @PostMapping
    public ResponseEntity<PostDetailResponseDto> addPost(@RequestBody PostRequestDto postRequestDto, @LoginUser UserDto currentUser) {
        return ResponseEntity.ok(boardService.createPost(postRequestDto, currentUser));
    }

    @GetMapping
    public ResponseEntity<List<PostDetailResponseDto>> getAllPosts() {
        return ResponseEntity.ok(boardService.findAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getPostDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetailResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto,
                                                            @LoginUser UserDto currentUser) {
        return ResponseEntity.ok(boardService.updatePostById(id, postRequestDto,currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @LoginUser UserDto currentUser) {
        boardService.deletePostById(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}
