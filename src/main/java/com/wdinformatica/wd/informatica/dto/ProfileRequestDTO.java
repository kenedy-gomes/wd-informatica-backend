package com.wdinformatica.wd.informatica.dto;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.domain.user.UserRole;

public record ProfileRequestDTO(String id,
                                String name,
                                String email,
                                String cpf,
                                String dataNascimento,
                                String sexo,
                                UserRole role,
                                String cep,
                                String endereco,
                                String bairro,
                                String cidade,
                                String estado, String complemento) {
    public static ProfileRequestDTO fromUsuario (User user){
        return new ProfileRequestDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getDataNascimento(),
                user.getSexo(),
                user.getRole(),
                user.getAddress().getCep(),
                user.getAddress().getEndereco(),
                user.getAddress().getBairro(),
                user.getAddress().getCidade(),
                user.getAddress().getEstado(),
                user.getAddress().getComplemento()
        );
    }
}
