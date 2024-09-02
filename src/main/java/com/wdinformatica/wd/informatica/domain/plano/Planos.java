package com.wdinformatica.wd.informatica.domain.plano;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wdinformatica.wd.informatica.domain.solicitacao.PlanRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "planos")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "planRequests"})
public class Planos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "megas", nullable = false, length = 50)
    private String megas;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "plano", nullable = false, length = 100)
    private String plano;

    @Column(name = "servicos", nullable = false, length = 255)
    private String servicos;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanRequest> planRequests;
}
