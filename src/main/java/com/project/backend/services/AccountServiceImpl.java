package com.project.backend.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.models.Role;
import com.project.backend.models.User;
import com.project.backend.repositories.RoleRepository;
import com.project.backend.repositories.UserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Role createNewRole(Role role) {
        Role roleExist = roleRepository.findRoleByName(role.getName()).orElse(null);
        if (roleExist != null) {
            throw new RuntimeException("Role already exist");
        } else {
            return roleRepository.save(role);
        }
    }

    @Override
    public User createNewUser(User user) {
        User userExist = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (userExist != null) {
            throw new RuntimeException("User already exist");
        } else {
            Role userRole = roleRepository.findRoleByName("USER").orElse(null);
            user.setRoles((Set.of(userRole)));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    @Override
    public boolean addRoleToUser(String username, String name) {
        User user = userRepository.findByUsername(username).orElse(null);
        Role role = roleRepository.findRoleByName(name).orElse(null);
        if (user != null && role != null) {
            if (user.getRoles().add(role)) {
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User updateUser(String id, User user) {
        Long userId = Long.parseLong(id);
        User userExist = userRepository.findById(userId).orElse(null);

        if (userExist != null) {
            userExist.setFirstName(user.getFirstName());
            userExist.setLastName(user.getLastName());
            userExist.setAddress(user.getAddress());
            userExist.setPhone(user.getPhone());

            return userRepository.save(userExist);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteUser(String id) {
        Long userId = Long.parseLong(id);
        User userExist = userRepository.findById(userId).orElse(null);
        userExist.setRoles(null);
        userRepository.save(userExist);
        userRepository.delete(userExist);
    }

    @Override
    public User findUserById(String id) {
        Long userId = Long.parseLong(id);
        return userRepository.findById(userId).orElse(null);
    }

}
