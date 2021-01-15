package com.example.hroauth.services;

import com.example.hroauth.dto.User;
import com.example.hroauth.feignclients.UserFeignClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClients userFeignClients;

    public User findByEmail(String email) {
        final User user = this.userFeignClients.findByEmail(email).getBody();
        if (user == null) {
            logger.error("Email not found: " + email);
            throw new UsernameNotFoundException("Email not found.");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.findByEmail(email);
    }
}
