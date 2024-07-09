package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.repositories.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    public Optional<Planos> findById(@PathVariable String id) {
        return planosRepository.findById(id);
    }

}
