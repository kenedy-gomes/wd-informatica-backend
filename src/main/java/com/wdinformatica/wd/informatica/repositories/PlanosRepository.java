package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanosRepository extends JpaRepository<Planos, String> {
    Optional<Planos> findByPlano(String plano);
}
