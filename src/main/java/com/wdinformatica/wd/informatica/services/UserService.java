package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
