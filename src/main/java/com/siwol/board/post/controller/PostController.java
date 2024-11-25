package com.siwol.board.post.controller;

import com.siwol.board.post.dto.request.PostRequestDto;
import com.siwol.board.post.dto.response.PostDetailResponseDto;
import com.siwol.board.post.service.PostService;

import com.siwol.board.user.auth.LoginUser;
import com.siwol.board.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class PostController {
    private final PostService postService;

    @GetMapping
    public String getAllPosts(Model model) {
        model.addAttribute("boards", postService.findAllPosts());
        return "boards/list";
    }

    @GetMapping("/new")
    public String addPostForm() {
        return "boards/create";
    }

    @PostMapping
    public String addPost(@ModelAttribute PostRequestDto postRequestDto, @LoginUser UserDto currentUser) {
        PostDetailResponseDto newBoard = postService.createPost(postRequestDto, currentUser);
        return "redirect:/boards/" + newBoard.getId();
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostDetail(id));
        return "boards/detail";
    }

    @GetMapping("/{id}/edit")
    public String updatePostForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPostDetail(id));
        return "boards/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostRequestDto postRequestDto,
                             @LoginUser UserDto currentUser) {
        PostDetailResponseDto updatePost = postService.updatePostById(id, postRequestDto, currentUser);
        return "redirect:/boards/" + updatePost.getId();
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, @LoginUser UserDto currentUser) {
        postService.deletePostById(id, currentUser);
        return "redirect:/boards";
    }
}