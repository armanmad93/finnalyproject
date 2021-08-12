//package com.example.techthink.config;
//
//import com.example.techthink.persistence.User;
//import com.example.techthink.service.UserService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserService userService;
//
//    public CustomUserDetailService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String term) throws UsernameNotFoundException {
//        User user = userService.loadByUsernameOrEmail(term);
//        //return new CustomUserDetails(user);
//        return null;
//    }
//}
