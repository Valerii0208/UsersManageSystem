package com.example.crm_project.service.impl;

import com.example.crm_project.entity.User;
import com.example.crm_project.repository.UserRepository;
import com.example.crm_project.service.UserService;
import com.example.crm_project.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Iterable<User> allUsers = userRepository.findAll();
        allUsers.forEach(userList::add);
        return userList;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User width id: " + id + " not found exception");
        } else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        throw new UserNotFoundException("User width id " + id + " not found ");
    }
}