package com.siwol.board.controller;

import com.siwol.board.dto.request.BoardRequestDto;
import com.siwol.board.dto.response.BoardResponseDto;
import com.siwol.board.repository.BoardRepository;
import com.siwol.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> addPost(@RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok(boardService.createPost(boardRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllPosts() {
        return ResponseEntity.ok(boardService.getPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity.ok(boardService.updatePostById(id,boardRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
