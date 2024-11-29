package com.siwol.board.post.application.search;

import com.siwol.board.post.application.search.type.SearchType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchTypes {
    private final List<SearchType> searchTypes;

    public SearchType findByType(String type) {
        return searchTypes.stream()
                .filter(s -> s.isSatisfiedBy(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 검색 타입입니다."));
    }
}
