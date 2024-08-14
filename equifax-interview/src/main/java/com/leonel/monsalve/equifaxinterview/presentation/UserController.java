package com.leonel.monsalve.equifaxinterview.presentation;

import com.leonel.monsalve.equifaxinterview.data.entity.User;
import com.leonel.monsalve.equifaxinterview.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.created(URI.create("/api/v1/user")).build();
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
        Boolean login = userService.login(username, password);
        if (Boolean.TRUE.equals(login))
            return ResponseEntity.ok().body(true);
        return ResponseEntity.badRequest().body(false);
    }

}
