package com.siwol.board.user.controller;

import com.siwol.board.user.dto.request.LoginRequestDto;
import com.siwol.board.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "accounts/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto loginRequestDto, HttpServletRequest request) {
        try {
            userService.login(request, loginRequestDto);
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            return "accounts/login";
        }
    }
}
