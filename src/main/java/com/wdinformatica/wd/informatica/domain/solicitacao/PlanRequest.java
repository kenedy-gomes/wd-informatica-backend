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
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"planRequests"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnoreProperties({"planRequests"})
    private Planos plan;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "solicitado", nullable = false, length = 10)
    private boolean solicitado = false;
}
