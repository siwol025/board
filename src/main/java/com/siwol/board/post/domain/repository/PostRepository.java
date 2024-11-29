package com.siwol.board.post.domain.repository;

import com.siwol.board.post.domain.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String keyword);

    List<Post> findByContentsContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Post p "
            + "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) "
            + "OR LOWER(p.contents) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> findByTitleOrContentsContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT p FROM Post p WHERE LOWER(p.author.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> findByAuthorUsernameContainingIgnoreCase(@Param("keyword") String keyword);
}
