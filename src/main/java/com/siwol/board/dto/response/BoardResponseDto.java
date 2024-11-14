package com.siwol.board.dto.response;

import com.siwol.board.domain.Board;
import com.siwol.board.dto.request.BoardRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;

    public static BoardResponseDto of (Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContents());
    }
}
