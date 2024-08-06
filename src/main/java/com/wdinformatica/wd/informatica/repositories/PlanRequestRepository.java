package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.solicitacao.PlanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRequestRepository  extends JpaRepository<PlanRequest, String> {
}
