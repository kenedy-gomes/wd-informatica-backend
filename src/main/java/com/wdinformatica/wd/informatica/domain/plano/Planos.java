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
    private String id;
    private String megas;
    private String description;
    private String plano;
    private String servicos;

    @OneToMany(mappedBy = "plan")
    private List<PlanRequest> planRequests;
}
