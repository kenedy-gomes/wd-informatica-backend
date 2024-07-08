package com.wdinformatica.wd.informatica.dto;

import com.wdinformatica.wd.informatica.domain.user.User;

import java.time.LocalDate;
import java.util.Date;

public record profileRequestDTO(String name, String email, String password, String cpf, LocalDate data_nascimento, String sexo) {
    public static profileRequestDTO fromUsuario (User user){
        return new profileRequestDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCpf(),
                user.getData_nascimento(),
                user.getSexo()
        );
    }
}
