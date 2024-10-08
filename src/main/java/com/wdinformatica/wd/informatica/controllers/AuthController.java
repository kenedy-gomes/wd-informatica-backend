package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.address.Address;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.dto.LoginRequestDTO;
import com.wdinformatica.wd.informatica.dto.RegisterRequestDTO;
import com.wdinformatica.wd.informatica.dto.ResponseDTO;
import com.wdinformatica.wd.informatica.infra.security.TokenService;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import com.wdinformatica.wd.informatica.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO body){
        Optional<User> user = this.repository.findByCpf(body.cpf());
        if (user.isPresent() && passwordEncoder.matches(body.password(), user.get().getPassword())) {
            String token = this.tokenService.generateToken(user.get());

            // Criação do cookie HttpOnly e Secure
            ResponseCookie cookie = ResponseCookie.from("sessionToken", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(24 * 60 * 60) // 1 dia
                    .build();

            return ResponseEntity.ok()
                    .header("Set-Cookie", cookie.toString())
                    .body(new ResponseDTO(user.get().getId(), user.get().getName(), user.get().getEmail(), user.get().getRole(), user.get().getCpf(), user.get().getDataNascimento(), user.get().getSexo(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO body){
        Optional<User> userByEmail = this.repository.findByEmail(body.email());
        Optional<User> userByCpf = this.repository.findByCpf(body.cpf());
        if (userByEmail.isEmpty() && userByCpf.isEmpty()) {
            User newUser = new User();
            newUser.setId(body.id());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setRole(body.role());
            newUser.setCpf(body.cpf());
            newUser.setDataNascimento(body.dataNascimento());
            newUser.setSexo(body.sexo());

            Address newAddres = new Address();
            newAddres.setId(body.id());
            newAddres.setCep(body.cep());
            newAddres.setEndereco(body.endereco());
            newAddres.setComplemento(body.complemento());
            newAddres.setBairro(body.bairro());
            newAddres.setEstado(body.estado());
            newAddres.setCidade(body.cidade());

            newUser.setAddress(newAddres);

            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);

            // Criação do cookie HttpOnly e Secure
            ResponseCookie cookie = ResponseCookie.from("sessionToken", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(24 * 60 * 60) // 1 dia
                    .build();

            return ResponseEntity.ok()
                    .header("Set-Cookie", cookie.toString())
                    .body(new ResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getRole(), newUser.getCpf(), newUser.getDataNascimento(), newUser.getSexo(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
