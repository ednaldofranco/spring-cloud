package com.example.hruser.resources;

import com.example.hruser.entities.User;
import com.example.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        User user = this.userRepository.findById(id).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<User> findById(@RequestParam String email) {
        User user = this.userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}
