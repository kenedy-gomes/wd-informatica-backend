package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.solicitacao.PlanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface PlanoRquestRepository  extends JpaRepository<PlanRequest, String> {
    @Query("SELECT pr FROM PlanRequest pr WHERE pr.user.id = :userId")
    List<PlanRequest> findAllByUserId(@Param("userId") String userId);
}
