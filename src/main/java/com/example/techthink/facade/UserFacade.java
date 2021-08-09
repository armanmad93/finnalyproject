package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.RegisterRequest;
import com.example.techthink.controller.model.UserResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.User;
import com.example.techthink.service.DTO.UserDTO;
import com.example.techthink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

@Facade
public class UserFacade {
    @Autowired
    UserService userService;
    private final Converter converter;

    public UserFacade(Converter converter) {
        this.converter = converter;
    }


    public UserResponse registerStudent(RegisterRequest request){
        UserDTO userDTO = convertToDTO(request);
        User registeredUser = userService.register(userDTO);
        UserResponse userResponse = converter.fromUserToResponse(registeredUser);
        return userResponse;

    }

    public UserResponse addProfessor(RegisterRequest request){
        UserDTO userDTO = convertToDTO(request);
        User professor = userService.addProfessor(userDTO);
        UserResponse userResponse = converter.fromUserToResponse(professor);
        return userResponse;
    }


    private UserDTO convertToDTO(RegisterRequest request){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(request.getFirstName());
        userDTO.setLastName(request.getLastName());
        userDTO.setUserName(request.getUserName());
        userDTO.setMail(request.getMail());
        userDTO.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        return userDTO;
    }
}
