package com.siwol.board.dto.request;

import com.siwol.board.domain.Board;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String contents;

    public Board toBoard() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .build();
    }
}
