package com.project.backend.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.project.backend.services.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty(message = "Mot de passe obligatoire")
    @Size(min = 6, max = 100, message = "Mot de passe doit avoir au moins 6 caractères")
    private String password;

    @NotEmpty(message = "Email obligatoire")
    @ValidEmail(message = "Email format is not valid")
    private String email;

    @NotEmpty(message = "Le nom ne peut pas être vide")
    private String firstName;

    @NotEmpty(message = "Le prénom ne peut pas être vide")
    private String lastName;
    private String phone;
    private String address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
