package com.example.hroauth.feignclients;

import com.example.hroauth.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClients {

    @GetMapping("/search")
    ResponseEntity<User> findByEmail(@RequestParam String email);
}
