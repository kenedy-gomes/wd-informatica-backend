package com.wdinformatica.wd.informatica.dto;

import com.wdinformatica.wd.informatica.domain.user.UserRole;

public record ResponseDTO(String name, String email, UserRole role, Long cpf, String token) {
}
