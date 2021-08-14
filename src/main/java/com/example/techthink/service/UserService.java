package com.example.techthink.service;

import com.example.techthink.persistence.User;
import com.example.techthink.service.DTO.UserDTO;

import java.util.List;

public interface UserService {

    User loadByUsernameOrEmail(String term);

    User register(UserDTO request);
    User addProfessor(UserDTO request);
    User readById(Long id);
    List<User> readAll();
    User update(Long id, UserDTO request);
    Boolean delete(Long id);

}
