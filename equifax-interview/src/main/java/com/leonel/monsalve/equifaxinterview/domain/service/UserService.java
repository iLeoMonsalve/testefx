package com.leonel.monsalve.equifaxinterview.domain.service;

import com.leonel.monsalve.equifaxinterview.data.entity.User;

public interface UserService {
    Boolean login(String username, String password);
    void register(User user);
}
