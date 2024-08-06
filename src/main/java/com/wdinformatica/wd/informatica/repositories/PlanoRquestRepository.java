package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.solicitacao.PlanRequest;
import com.wdinformatica.wd.informatica.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanoRquestRepository  extends JpaRepository<PlanRequest, String> {
}
