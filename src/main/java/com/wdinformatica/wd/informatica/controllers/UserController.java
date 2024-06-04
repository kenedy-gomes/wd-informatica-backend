package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import com.wdinformatica.wd.informatica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser(){
        return  userService.getAllUsers();
    }
}
