package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, String > {
}
