package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    User findUserById(Long userId);
    List<User> allUsers();
    void saveUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Long userId);
    User findByUsername(String username);
    List<Role> listRoles();
}
