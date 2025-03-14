package com.siwol.board.comment.controller;

import com.siwol.board.comment.dto.request.CommentRequestDto;
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
@RequestMapping("/boards/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public String addComment(@PathVariable Long postId,
                             @LoginUser UserDto loginUser,
                             @ModelAttribute CommentRequestDto commentRequest) {
        CommentRequestDto commentRequestDto = CommentRequestDto.of(postId, loginUser, commentRequest);
        commentService.addComment(commentRequestDto);

        return "redirect:/boards/" + postId;
    }

    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @LoginUser UserDto loginUser) {
        commentService.deleteComment(commentId, loginUser);

        return "redirect:/boards/" + postId;
    }
}
