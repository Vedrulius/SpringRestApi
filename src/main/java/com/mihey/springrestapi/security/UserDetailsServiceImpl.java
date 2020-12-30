package com.mihey.springrestapi.security;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Not found" + userName));
        return new UserDetailsImpl(user);
    }
}

