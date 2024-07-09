package com.wdinformatica.wd.informatica.dto;

import com.wdinformatica.wd.informatica.domain.user.UserRole;

import java.time.LocalDate;
import java.util.Date;

public record ResponseDTO(String name, String email, UserRole role, String cpf, String data_nascimento, String sexo, String token) {
}
