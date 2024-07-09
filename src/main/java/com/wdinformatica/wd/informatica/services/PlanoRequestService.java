package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.dto.PlanoRequestDTO;
import com.wdinformatica.wd.informatica.repositories.PlanoRquestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoRequestService {
    @Autowired
    private PlanoRquestRepository planoRepository;

    public PlanoRequestDTO createRequest(User user, Optional<Planos> plano) {
        PlanoRequestDTO request = new PlanoRequestDTO();
        request.setUser(user);
        request.setPlano(plano.get());
        return planoRepository.save(request);
    }

    public List<PlanoRequestDTO> getPendingRequests() {
        return planoRepository.findByApprovedFalse();
    }

    public PlanoRequestDTO approveRequest(String requestId) {
        PlanoRequestDTO request = planoRepository.findById(requestId).orElseThrow();
        request.setApproved(true);
        planoRepository.save(request);
        return request;
    }
}
