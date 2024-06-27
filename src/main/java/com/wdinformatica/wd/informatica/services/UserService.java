package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found for email: " + email)));
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String id, User updateUser){
        User existingUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        existingUser.setName(updateUser.getName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setPassword(updateUser.getPassword());
        existingUser.setRole(updateUser.getRole());
        existingUser.setCpf(updateUser.getCpf());
        return userRepository.save(existingUser);
    }

}
