package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.dto.LoginRequestDTO;
import com.wdinformatica.wd.informatica.dto.RegisterRequestDTO;
import com.wdinformatica.wd.informatica.dto.ResponseDTO;
import com.wdinformatica.wd.informatica.infra.security.TokenService;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import com.wdinformatica.wd.informatica.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  @Valid LoginRequestDTO body){
        User user = this.repository.findByCpf(String.valueOf(Long.valueOf(body.cpf()))).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), user.getEmail(), user.getRole(), user.getCpf(), user.getData_nascimento(), user.getSexo(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());
        Optional<User> users = this.repository.findByCpf(body.cpf());
        if(user.isEmpty() && users.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setRole(body.role());
            newUser.setCpf(body.cpf());
            newUser.setData_nascimento(body.data_nascimento());
            newUser.setSexo(body.sexo());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), newUser.getEmail(), newUser.getRole(), newUser.getCpf(), newUser.getData_nascimento(), newUser.getSexo(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
