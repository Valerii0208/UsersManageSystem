package com.example.crm_project.service;

import com.example.crm_project.entity.User;
import com.example.crm_project.service.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id) throws UserNotFoundException;

    User getUserById(Long id) throws UserNotFoundException;
}
