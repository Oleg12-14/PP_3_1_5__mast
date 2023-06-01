package ru.kata.spring.boot_security.demo.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.Collections;

@Component
public class Loader implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    public Loader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        User admin = new User("admin", "Admin", "Adminov", "admin@mail.com", "admin", Collections.singleton(adminRole));
        User user = new User("user", "User", "Userov", "user@mail.com", "user", Collections.singleton(userRole));

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
