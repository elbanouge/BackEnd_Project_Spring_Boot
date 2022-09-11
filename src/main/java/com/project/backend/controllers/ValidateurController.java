package com.project.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.models.Validateur;
import com.project.backend.repositories.ValidateurRepository;

@RestController
@RequestMapping("api-rest/account/validateurs/")
public class ValidateurController {

    @Autowired
    private ValidateurRepository validationRepository;

    @GetMapping("addAll")
    public String validateur() {
        // add des validateurs
        Validateur validateur = new Validateur(null, "email",
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",
                "L'Adresse email non valide", "Adresse email valide");
        validationRepository.save(validateur);
        return "ok";
    }

    @GetMapping("findAll")
    public Iterable<Validateur> findAll() {
        return validationRepository.findAll();
    }

    @GetMapping("findByNomChamp")
    public Validateur findByNomChamp(@RequestParam String champ) {
        return validationRepository.findByNomChamp(champ).orElse(null);
    }
}
