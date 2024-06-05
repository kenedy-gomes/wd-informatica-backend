package com.wdinformatica.wd.informatica.domain.plano;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "planos")
@Getter
@Setter
@NoArgsConstructor
public class Planos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String plano;
    private String description;
}
