package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.dto.PlanoRequestDTO;
import com.wdinformatica.wd.informatica.repositories.UserRepository;
import com.wdinformatica.wd.informatica.services.PlanoRequestService;
import com.wdinformatica.wd.informatica.services.PlanosService;
import com.wdinformatica.wd.informatica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planorequests")
public class PlanoRequestController {
    @Autowired
    private PlanoRequestService service;
    @Autowired
    private UserService userService;
    @Autowired
    private PlanosService planosService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public PlanoRequestDTO createRequest(@RequestParam String userId, @RequestParam String planoId) {
        User user = userService.findById(userId);
        Optional<Planos> plano = planosService.findById(planoId);
        return service.createRequest(user, plano);
    }

    @GetMapping("/pending")
    public List<PlanoRequestDTO> getPendingRequests() {
        return service.getPendingRequests();
    }

    @PostMapping("/approve/{id}")
    public PlanoRequestDTO approveRequest(@PathVariable String id) {
        PlanoRequestDTO request = service.approveRequest(id);
        User user = request.getUser();
        user.setPlano(request.getPlano());
        userRepository.save(user);
        return request;
    }
}
