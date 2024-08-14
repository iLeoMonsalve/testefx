package com.leonel.monsalve.equifaxinterview.domain.service.impl;

import com.leonel.monsalve.equifaxinterview.data.entity.User;
import com.leonel.monsalve.equifaxinterview.data.repository.UserRepository;
import com.leonel.monsalve.equifaxinterview.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean login(String username, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.filter(value -> passwordEncoder.matches(rawPassword, value.getPassword())).isPresent();
    }

    @Override
    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
