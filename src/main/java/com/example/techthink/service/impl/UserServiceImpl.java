package com.example.techthink.service.impl;


import com.example.techthink.persistence.User;
import com.example.techthink.persistence.repository.UserRepository;
import com.example.techthink.service.DTO.UserDTO;
import com.example.techthink.service.RoleService;
import com.example.techthink.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User loadByUsernameOrEmail(String term) {
        User user = userRepository.loadUserByUsernameOrEmail(term);
        return user;
    }

    @Override
    public User register(UserDTO request){
        User user = buildUser(request);
        User savedUser = userRepository.save(user);
        User addRolledUser = addRoleToUser(savedUser.getId(), 2L);  //STUDENT
        return addRolledUser;
    }

    public User addProfessor(UserDTO request){
        User user = buildUser(request);
        User savedUser = userRepository.save(user);
        User addRolledUser = addRoleToUser(savedUser.getId(), 3L); //PROFESSOR
        return addRolledUser;
    }

    private User buildUser(UserDTO request){
        User user = new User();
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setUsername(request.getUserName());
        user.setEmail(request.getMail());
        user.setPassword(request.getPassword());
//        Role roleByName = roleService.findRoleByName(request.getRole().name());
        //user.addRole(roleService.findRoleByName(request.getRole().name()));
        //user.addRole(roleService.findRoleByName(RoleName.USER.name()));
        return user;
    }

    private User addRoleToUser(Long userId, Long roleId){
        User byId = userRepository.getById(userId);
        byId.getRoles().add(roleService.getRoleById(roleId));
        User saved = userRepository.save(byId);
        return saved;
    }



}
