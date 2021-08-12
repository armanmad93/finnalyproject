package com.example.techthink.service;

import com.example.techthink.persistence.User;
import com.example.techthink.service.DTO.UserDTO;

public interface UserService {

    User loadByUsernameOrEmail(String term);

    User register(UserDTO request);

    User addProfessor(UserDTO request);
}
