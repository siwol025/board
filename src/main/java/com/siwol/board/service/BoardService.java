package com.siwol.board.service;

import com.siwol.board.domain.Board;
import com.siwol.board.dto.request.BoardRequestDto;
import com.siwol.board.dto.response.BoardResponseDto;
import com.siwol.board.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponseDto createPost(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(new Board(boardRequestDto));
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> getPosts() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto getPostById(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::new).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }
}
