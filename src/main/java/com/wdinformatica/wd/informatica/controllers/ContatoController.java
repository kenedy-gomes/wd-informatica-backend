package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.contato.Contato;
import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> getAllPlanos() {
        return contatoService.getContato();
    }

    @PostMapping
    public String CreateContato (@RequestBody Contato contato){
        List<Contato> cont = contatoService.getContato();

        contatoService.save(contato);
        return "Plano cadastrado com sucesso!";
    }

}
