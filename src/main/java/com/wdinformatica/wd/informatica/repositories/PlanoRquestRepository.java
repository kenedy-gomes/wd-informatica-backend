package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.dto.PlanoRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanoRquestRepository  extends JpaRepository<PlanoRequestDTO, String> {
    List<PlanoRequestDTO> findByApprovedFalse();
}
