package com.project.backend.controllers;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.models.Role;
import com.project.backend.models.User;
import com.project.backend.services.AccountService;

@RestController
@RequestMapping("api-rest/account/")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("home")
    public String home() {
        return "Hello World";
    }

    @PostMapping("addNewRole")
    public ResponseEntity<Role> addNewRole(@RequestBody Role role) {
        return ResponseEntity.ok(accountService.createNewRole(role));
    }

    @PostMapping("addNewUser")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        User savedUser = accountService.createNewUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("addRoleToUser/{username}/{name}")
    public ResponseEntity<Boolean> addRoleToUser(@PathVariable String username, @PathVariable String name) {
        return ResponseEntity.ok(accountService.addRoleToUser(username, name));
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(accountService.updateUser(id, user));
    }

    @GetMapping("all")
    public List<User> getAllUsers() {
        return accountService.getUsers();
    }

    @GetMapping("profile")
    public User profile(Principal principal) {
        UserDetails uDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = accountService.findUserByUsername(uDetails.getUsername());
        return user;
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) {
        return accountService.findUserById(id);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        accountService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}
