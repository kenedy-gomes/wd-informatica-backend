package com.wdinformatica.wd.informatica.domain.contato;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contato")
@Getter
@Setter
@NoArgsConstructor
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String assunto;
    private String mensagem;
}
