package com.siwol.board.post.common;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum SearchType {
    TITLE("s_title"),
    CONTENT("s_content"),
    TITLE_OR_CONTENT("s_title_content"),
    AUTHOR("s_author"),
    NOTHING("nothing");

    private final String type;

    SearchType(String type) {
        this.type = type;
    }

    public static SearchType findSearchType(String type) {
        return Arrays.stream(SearchType.values())
                .filter(s -> s.getType().equals(type))
                .findFirst()
                .orElse(NOTHING);
    }
}
