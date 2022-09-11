package com.project.backend.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.backend.models.Role;
import com.project.backend.models.User;
import com.project.backend.services.AccountService;

@Component
public class AppController {

    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void init() {

        try {
            accountService.createNewRole(new Role(null, "ADMIN"));
            accountService.createNewRole(new Role(null, "USER"));

            accountService
                    .createNewUser(
                            new User(null, "admin", "admin", "admin@gmail.com", "test", "test", "test", "test", null));
            accountService
                    .createNewUser(new User(null, "user01", "user01", "user01@gmail.com", "test", "test", "test",
                            "test", null));
            accountService
                    .createNewUser(new User(null, "user02", "user02", "user02@gmail.com", "test", "test", "test",
                            "test", null));

            accountService.addRoleToUser("admin", "ADMIN");

            System.out.println("\n ***********************************************************");
            System.out.println("\n AccountController initialized successfully");
            System.out.println("\n ***********************************************************");

        } catch (Exception e) {

            System.out.println("\n ***********************************************************");
            System.out.println("\n Error while creating " + e.getMessage());
            System.out.println("\n ***********************************************************");

        }
    }
}
