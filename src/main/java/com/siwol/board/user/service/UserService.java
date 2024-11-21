package com.siwol.board.user.service;

import com.siwol.board.user.domain.entitiy.User;
import com.siwol.board.user.dto.request.UserRequestDto;
import com.siwol.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserRequestDto userRequestDto) {
        checkDuplicateUser(userRequestDto.getUsername());
        userRepository.save(userRequestDto.toUser());
    }

    public void checkDuplicateUser(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }
    }
}
