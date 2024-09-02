package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.solicitacao.PlanRequest;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.domain.user.UserRole;
import com.wdinformatica.wd.informatica.dto.SolicitacaoPlanoRequest;
import com.wdinformatica.wd.informatica.repositories.PlanoRquestRepository;
import com.wdinformatica.wd.informatica.services.PlanosService;
import com.wdinformatica.wd.informatica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/planos")
public class PlanosController {

    @Autowired
    PlanosService planosService;

    @Autowired
    UserService userService;

    @Autowired
    PlanoRquestRepository planoRquestRepository;

    //REQUISIÇÃO PARA PEGAR TODOS OS PLANOS
    @GetMapping
    public List<Planos> getAllPlanos() {
        return planosService.findAll();
    }

    //REQUISIÇÃO PARA PEGAR PLANOS POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Planos> getPlanosById(@PathVariable String id) {
        Optional<Planos> plan = planosService.findById(id);
        if(plan.isPresent()) {
            return ResponseEntity.ok(plan.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //REQUISIÇÃO PARA CRIAR PLANOS
    @PostMapping
    public String createPlano(@RequestBody Planos plano) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = userService.getByEmail(userDetails.getUsername());

        if (optionalUser.isEmpty()) {
            return "Usuário não encontrado.";
        }

        User user = optionalUser.get();

        if (user.getRole() != UserRole.ADMIN) {
            return "Você não tem permissão para cadastrar um plano.";
        }

        planosService.save(plano);
        return "Plano cadastrado com sucesso!";
    }

    //REQUISIÇÃO PARA EDITAR PLANOS
    @PutMapping("/{id}")
    public Planos updatePlanos(@PathVariable String id, @RequestBody Planos plano) {
        return planosService.UpdatePlanos(id, plano);
    }

    //REQUISIÇÃO PARA DELETAR PLANOS
    @DeleteMapping("/{id}")
    public void DeletePlanos(@PathVariable String id){
         planosService.DeletePlanos(id);
    }

    //REQUSIÇÃO PARA ENVIAR A SOLICITAÇÃO
    @PostMapping("/request-plan")
    public PlanRequest requestPlan(@RequestBody SolicitacaoPlanoRequest planRequestDTO) {
        return planosService.requestPlan(planRequestDTO.userId(), planRequestDTO.planId());
    }

    //REQUISIÇÃO PARA PEGAR TODAS AS SOLICITAÇÕES POR ID
    @GetMapping("/user-plan-requests/{userId}")
    public List<PlanRequest> getAllPlanRequestsForUser(@PathVariable String userId) {
        return planosService.getAllPlanRequestsForUser(userId);
    }

    //REQUISIÇÃO PARA PEGAR TODOS AS SOLICITAÇÕES
    @GetMapping("/plan-requests")
    public Page<PlanRequest> getAllPlanRequests(Pageable pageable) {
        return planosService.getAllPlanRequests(pageable);
    }

    //REQUISIÇÃO PARA APROVAR A SOLICITAÇÃO
    @PostMapping("/approve-plan-request/{requestId}")
    public PlanRequest approvePlanRequest(@PathVariable String requestId) {
        return planosService.approvePlanRequest(requestId);
    }

    //REQUISIÇÃO PARA REJEITAR A SOLICITAÇÃO
    @PostMapping("/reject-plan-request/{requestId}")
    public ResponseEntity<PlanRequest> rejectPlanRequest(@PathVariable String requestId) {
        PlanRequest planRequest = planosService.rejectPlanRequest(requestId);
        return ResponseEntity.ok(planRequest);
    }
}
