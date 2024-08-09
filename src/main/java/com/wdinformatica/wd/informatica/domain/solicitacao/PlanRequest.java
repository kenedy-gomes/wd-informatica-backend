package com.wdinformatica.wd.informatica.domain.solicitacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plano_requests")
@Getter
@Setter
@NoArgsConstructor
public class PlanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"planRequests"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnoreProperties({"planRequests"})
    private Planos plan;

    private String status;

    private boolean solicitado = false;
}
