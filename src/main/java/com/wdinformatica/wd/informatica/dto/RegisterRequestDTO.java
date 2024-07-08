package com.wdinformatica.wd.informatica.dto;

import com.wdinformatica.wd.informatica.domain.user.UserRole;

import java.util.Date;

public record RegisterRequestDTO(String name, String email, String password, String cpf, Date data_nascimento, String sexo, UserRole role) {
}
