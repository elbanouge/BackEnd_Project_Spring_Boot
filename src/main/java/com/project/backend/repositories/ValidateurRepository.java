package com.project.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.backend.models.Validateur;

@Repository
public interface ValidateurRepository extends CrudRepository<Validateur, Long> {
    Optional<Validateur> findByNomChamp(String nomChamp);
}
