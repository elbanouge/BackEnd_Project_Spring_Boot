package com.project.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.backend.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
