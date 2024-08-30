package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.dto.ProfileRequestDTO;
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

    public Optional<User> getByCpf(String cpf) {
        return Optional.ofNullable(userRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("User not found for cpf" + cpf)));
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String id, ProfileRequestDTO updateUser){
        User existingUser = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        existingUser.setName(updateUser.name());
        existingUser.setEmail(updateUser.email());
        existingUser.setCpf(updateUser.cpf());
        existingUser.setDataNascimento(updateUser.dataNascimento());
        existingUser.setSexo(updateUser.sexo());
        existingUser.setAvatarUrl(updateUser.avatarUrl());
        return userRepository.save(existingUser);
    }


    public Long formatCpf(String cpf) {
        if (cpf != null) {
            String cleanedCpf = cpf.replaceAll("\\.", "").replaceAll("-", "");
            return Long.parseLong(cleanedCpf);
        }
        return null;
    }
}
