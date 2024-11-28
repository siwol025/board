package com.siwol.board.comment.controller;

import com.siwol.board.comment.dto.CommentRequestDto;
import com.siwol.board.comment.service.CommentService;
import com.siwol.board.user.auth.LoginUser;
import com.siwol.board.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/{postId}")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public String addComment(@PathVariable Long postId,
                             @LoginUser UserDto loginUser,
                             @ModelAttribute CommentRequestDto commentRequest) {
        CommentRequestDto commentRequestDto = CommentRequestDto.of(postId, loginUser, commentRequest);
        commentService.addComment(commentRequestDto);

        return "redirect:/boards/" + postId;
    }
}
