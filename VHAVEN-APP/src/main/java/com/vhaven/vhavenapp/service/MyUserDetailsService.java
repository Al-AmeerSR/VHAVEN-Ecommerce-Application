package com.vhaven.vhavenapp.service;

import com.vhaven.vhavenapp.entity.User;
import com.vhaven.vhavenapp.entity.UserPrincipal;
import com.vhaven.vhavenapp.exception.UserNotFoundException;
import com.vhaven.vhavenapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("UserNotFound with email id :"+email);
        }
        return new UserPrincipal(user);
    }
}
