package com.project.backend.services;

import java.util.List;

import com.project.backend.models.Role;
import com.project.backend.models.User;

public interface AccountService {

    public Role createNewRole(Role role);

    public User createNewUser(User user);

    public User findUserByUsername(String username);

    public User findUserById(String id);

    public boolean addRoleToUser(String username, String name);

    public List<User> getUsers();

    public User updateUser(String id, User user);

    public void deleteUser(String id);
}
