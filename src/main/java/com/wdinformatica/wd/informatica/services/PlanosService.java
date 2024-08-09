package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.solicitacao.PlanRequest;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.repositories.PlanoRquestRepository;
import com.wdinformatica.wd.informatica.repositories.PlanosRepository;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PlanosService {

    @Autowired
    PlanosRepository planosRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanoRquestRepository planoRquestRepository;

    public List<Planos> findAll() {
        return planosRepository.findAll();
    }
    public Planos save(Planos planos) {
        return planosRepository.save(planos);
    }

    public Optional<Planos> findById(@PathVariable String id) {
        return planosRepository.findById(id);
    }

    public Planos UpdatePlanos(String id, Planos plano) {
        Planos existingPlanos = planosRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        existingPlanos.setPlano(plano.getPlano());
        existingPlanos.setMegas(plano.getMegas());
        existingPlanos.setServicos(plano.getServicos());
        existingPlanos.setDescription(plano.getDescription());
        return planosRepository.save(existingPlanos);
    }

    public void DeletePlanos(@PathVariable String id){
         planosRepository.deleteById(id);

    }

    public PlanRequest requestPlan(String userId, String planId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Planos plan = planosRepository.findById(planId).orElse(null);
        if (plan == null) {
            throw new IllegalArgumentException("Plan not found");
        }

        PlanRequest planRequest = new PlanRequest();
        planRequest.setUser(user);
        planRequest.setPlan(plan);
        planRequest.setStatus("PENDENTE");
        planRequest.setSolicitado(true);
        return planoRquestRepository.save(planRequest);
    }

    public Page<PlanRequest> getAllPlanRequests(Pageable pageable) {
        return planoRquestRepository.findAll(pageable);
    }

    public List<PlanRequest> getAllPlanRequestsForUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return planoRquestRepository.findAllByUserId(user.getId());
    }

    public PlanRequest approvePlanRequest(String requestId) {
        PlanRequest planRequest = planoRquestRepository.findById(requestId).orElse(null);
        if (planRequest != null) {
            planRequest.setStatus("APROVADO");
            planRequest.setSolicitado(true);
            return planoRquestRepository.save(planRequest);
        }
        return null;
    }

    public PlanRequest rejectPlanRequest(String requestId) {
        PlanRequest planRequest = planoRquestRepository.findById(requestId).orElse(null);
        if (planRequest != null) {
            planRequest.setStatus("RECUSADO");
            planRequest.setSolicitado(false);
            return planoRquestRepository.save(planRequest);
        }
        return null;
    }
}
