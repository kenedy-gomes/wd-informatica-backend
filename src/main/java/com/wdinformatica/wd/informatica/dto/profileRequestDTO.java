package com.wdinformatica.wd.informatica.dto;
import com.wdinformatica.wd.informatica.domain.user.User;
import java.time.LocalDate;

public record profileRequestDTO(String id, String name, String email, String cpf, String data_nascimento, String sexo) {
    public static profileRequestDTO fromUsuario (User user){
        return new profileRequestDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getData_nascimento(),
                user.getSexo()
        );
    }
}
