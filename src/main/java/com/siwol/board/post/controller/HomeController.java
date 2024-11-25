package com.siwol.board.post.controller;

import com.siwol.board.user.auth.LoginUser;
import com.siwol.board.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@LoginUser UserDto userDto, Model model) {
        if (userDto != null) {
            model.addAttribute("user", userDto);
        }
        return "home";
    }
}
