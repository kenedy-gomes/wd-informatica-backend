package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.contato.Contato;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;


    public List<Contato> getContato(){
        return contatoRepository.findAll();
    }

    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }

}
