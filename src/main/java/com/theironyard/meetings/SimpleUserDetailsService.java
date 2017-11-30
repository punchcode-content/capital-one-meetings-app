package com.theironyard.meetings;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
    Map<String,User> userMap;

    public SimpleUserDetailsService() {
        userMap = new HashMap<>();
        userMap.put("user", new User("password", "ROLE_USER"));
        userMap.put("admin", new User("password", "ROLE_USER,ROLE_ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMap.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(
                username,
                user.password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.roles)
        );
    }
}

class User {
    String password;
    String roles;

    public User(String password, String roles) {
        this.password = password;
        this.roles = roles;
    }
}