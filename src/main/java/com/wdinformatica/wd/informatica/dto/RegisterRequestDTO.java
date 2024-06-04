package com.wdinformatica.wd.informatica.dto;

import com.wdinformatica.wd.informatica.domain.user.UserRole;

public record RegisterRequestDTO(String name, String email, String password, UserRole role) {
}
