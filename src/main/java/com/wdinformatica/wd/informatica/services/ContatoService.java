package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.contato.Contato;
import com.wdinformatica.wd.informatica.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;


    public Page<Contato> getContato(Pageable pageable){
        return contatoRepository.findAll(pageable);
    }

    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }
}
