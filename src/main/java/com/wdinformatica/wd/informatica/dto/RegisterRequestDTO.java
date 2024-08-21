package com.wdinformatica.wd.informatica.dto;

import com.wdinformatica.wd.informatica.domain.user.UserRole;

public record RegisterRequestDTO(String id,
                                 String name,
                                 String email,
                                 String password,
                                 String cpf,
                                 String dataNascimento,
                                 String sexo,
                                 UserRole role,
                                 String cep,
                                 String endereco,
                                 String bairro,
                                 String cidade,
                                 String estado,
                                 String complemento) {
}
