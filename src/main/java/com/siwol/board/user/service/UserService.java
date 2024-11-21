package com.siwol.board.user.service;

import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.dto.UserDto;
import com.siwol.board.user.dto.request.LoginRequestDto;
import com.siwol.board.user.dto.request.UserRequestDto;
import com.siwol.board.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(UserRequestDto userRequestDto) {
        checkDuplicateUser(userRequestDto.getUsername());

        userRepository.save(User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword()).build()
        );
    }

    public void login(HttpServletRequest request, LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("찾을 수 없는 아이디입니다."));

        checkPassword(loginRequestDto, user);

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", UserDto.of(user));
    }

    public void checkDuplicateUser(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }
    }

    public void checkPassword(LoginRequestDto loginRequestDto, User user) {
        if(!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
    }
}
