package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.contato.Contato;
import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public Page<Contato> getAllPlanos(Pageable pageable) {
        return contatoService.getContato(pageable);
    }

    @PostMapping
    public String createContato(@RequestBody Contato contato, Pageable pageable){
        Page<Contato> cont = contatoService.getContato(pageable);
        contatoService.save(contato);
        return "Plano cadastrado com sucesso!";
    }


}
