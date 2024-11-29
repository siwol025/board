package com.siwol.board.post.application.search.type;

import com.siwol.board.post.domain.entity.Post;
import com.siwol.board.post.domain.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentsType implements SearchType{
    private static final String TYPE_NAME = "s_content";
    private final PostRepository postRepository;

    @Override
    public boolean isSatisfiedBy(String type) {
        return TYPE_NAME.equals(type);
    }

    @Override
    public List<Post> search(String keyword) {
        return postRepository.findByContentsContainingIgnoreCase(keyword);
    }
}
