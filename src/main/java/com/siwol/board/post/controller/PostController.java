package com.siwol.board.post.controller;

import com.siwol.board.post.dto.request.PostRequestDto;
import com.siwol.board.post.dto.response.PostResponseDto;
import com.siwol.board.post.service.PostService;

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
    private final PostService boardService;

    @GetMapping
    public String getAllPosts(Model model) {
        model.addAttribute("boards", boardService.findAllPosts());
        return "boards/list";
    }

    @GetMapping("/new")
    public String addPostForm() {
        return "boards/create";
    }

    @PostMapping
    public String addPost(@ModelAttribute PostRequestDto postRequestDto) {
        PostResponseDto newBoard = boardService.createPost(postRequestDto);
        return "redirect:/boards/" + newBoard.getId();
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findPostById(id));
        return "boards/detail";
    }

    @GetMapping("/{id}/edit")
    public String updatePostForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findPostById(id));
        return "boards/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostRequestDto postRequestDto) {
        PostResponseDto updatePost = boardService.updatePostById(id, postRequestDto);
        return "redirect:/boards/" + updatePost.getId();
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        boardService.deletePostById(id);
        return "redirect:/boards";
    }
}
