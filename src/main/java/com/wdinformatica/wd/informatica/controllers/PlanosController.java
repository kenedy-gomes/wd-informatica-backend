package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.repositories.PlanosRepository;
import com.wdinformatica.wd.informatica.services.PlanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
public class PlanosController {
    @Autowired
    PlanosService planosService;

    @Autowired
    PlanosRepository planoRepository;

    @GetMapping
    public List<Planos> getAllPlanos() {
        return planosService.findAll();
    }

    @PostMapping
    public Planos createPlanos(@RequestBody Planos planos) {
        return planosService.save(planos);
    }
}
