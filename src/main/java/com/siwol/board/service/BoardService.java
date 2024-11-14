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
        Board board = boardRepository.save(boardRequestDto.toBoard());
        return BoardResponseDto.of(board);
    }

    public List<BoardResponseDto> getPosts() {
        return boardRepository.findAll().stream().map(BoardResponseDto::of).toList();
    }

    public BoardResponseDto getPostById(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::of).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    public BoardResponseDto updatePostById(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Board updateBoard = board.update(boardRequestDto);

        return BoardResponseDto.of(updateBoard);
    }
}
