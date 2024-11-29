package com.siwol.board.post.application.search.type;

import com.siwol.board.post.domain.entity.Post;
import java.util.List;

public interface SearchType {
    boolean isSatisfiedBy(String type);
    List<Post> search(String keyword);
}
