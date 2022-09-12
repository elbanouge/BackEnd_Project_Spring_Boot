package com.project.backend.services;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.backend.models.Validateur;
import com.project.backend.repositories.ValidateurRepository;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Autowired
    private ValidateurRepository validateurRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        Validateur validateur = validateurRepository.findByNomChamp("email").orElse(null);

        if (email.matches(validateur.getRegex_champ())) {
            return true;
        }

        return false;
    }
}
