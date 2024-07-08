package com.wdinformatica.wd.informatica.controllers;

import com.auth0.jwt.JWT;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.dto.profileRequestDTO;
import com.wdinformatica.wd.informatica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser(){
        return  userService.getAllUsers();
    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }@GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader){
        try {
            String cpf = JWT.decode(authorizationHeader.replace("Bearer ", "")).getSubject();
            Optional<User> usuarioOptional = userService.getByEmail(cpf);
            if(usuarioOptional.isPresent()) {
                profileRequestDTO user = profileRequestDTO.fromUsuario(usuarioOptional.get());
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter perfil do usuário");
        }
    }



    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }


}
