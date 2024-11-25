package com.siwol.board.service;

import com.siwol.board.domain.entity.Board;
import com.siwol.board.dto.request.BoardRequestDto;
import com.siwol.board.dto.response.BoardResponseDto;
import com.siwol.board.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createPost(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(boardRequestDto.toBoard());
        return BoardResponseDto.of(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllPosts() {
        return boardRepository.findAll().stream().map(BoardResponseDto::of).toList();
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findPostById(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::of).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    @Transactional
    public BoardResponseDto updatePostById(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Board updateBoard = board.update(boardRequestDto);

        return BoardResponseDto.of(updateBoard);
    }

    @Transactional
    public void deletePostById(Long id) {
        boardRepository.deleteById(id);
    }
}
