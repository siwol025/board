package com.siwol.board.user.controller;

import com.siwol.board.user.dto.request.LoginRequestDto;
import com.siwol.board.user.dto.request.UserRequestDto;
import com.siwol.board.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(@ModelAttribute LoginRequestDto loginRequestDto,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {
        try {
            userService.login(request, loginRequestDto);
            return "redirect:" + redirectURL;
        } catch (IllegalArgumentException e) {
            return "accounts/login";
        }
    }

    @GetMapping("/signup")
    public String signUpForm() {
        return "accounts/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserRequestDto userRequestDto) {
        try {
            userService.signUp(userRequestDto);
            return "redirect:/accounts/login";
        } catch (IllegalArgumentException e) {
            return "accounts/signup";
        }
    }

    @PostMapping("/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
