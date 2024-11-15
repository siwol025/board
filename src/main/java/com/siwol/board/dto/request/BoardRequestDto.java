package com.siwol.board.dto.request;

import com.siwol.board.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRequestDto {
    private String title;
    private String contents;

    public Board toBoard() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .build();
    }

    @Builder
    public BoardRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
