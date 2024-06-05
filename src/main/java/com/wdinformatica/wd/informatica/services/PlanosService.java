package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.repositories.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanosService {
    @Autowired
    PlanosRepository planosRepository;

    public List<Planos> findAll() {
        return planosRepository.findAll();
    }
    public Planos save(Planos planos) {
        return planosRepository.save(planos);
    }
}
