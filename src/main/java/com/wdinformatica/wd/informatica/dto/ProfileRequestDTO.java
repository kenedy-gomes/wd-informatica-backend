package com.wdinformatica.wd.informatica.dto;
import com.wdinformatica.wd.informatica.domain.address.Address;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.domain.user.UserRole;

public record ProfileRequestDTO(String id,
                                String name,
                                String email,
                                String cpf,
                                String dataNascimento,
                                String sexo,
                                UserRole role,
                                String avatarUrl,
                                Address address) {
    public static ProfileRequestDTO fromUsuario (User user){
        return new ProfileRequestDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getDataNascimento(),
                user.getSexo(),
                user.getRole(),
                user.getAvatarUrl(),
                user.getAddress()
        );
    }
}
