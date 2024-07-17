package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.plano.Planos;
import com.wdinformatica.wd.informatica.domain.user.User;
import com.wdinformatica.wd.informatica.domain.user.UserRole;
import com.wdinformatica.wd.informatica.repositories.PlanosRepository;
import com.wdinformatica.wd.informatica.services.PlanosService;
import com.wdinformatica.wd.informatica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planos")
public class PlanosController {

    @Autowired
    PlanosService planosService;

    @Autowired
    UserService userService;


    @GetMapping
    public List<Planos> getAllPlanos() {
        return planosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planos> getPlanosById(@PathVariable String id) {
        Optional<Planos> plan = planosService.findById(id);
        if(plan.isPresent()) {
            return ResponseEntity.ok(plan.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<Planos> updatePlanos (@PathVariable String id, @RequestBody Planos planosDetails) {
        Optional<Planos> updatePlanos = planosService.updatePlanos(id, planosDetails);
        return updatePlanos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
