package ru.kata.spring.boot_security.demo.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;


import java.util.Collections;

@Component
public class Loader implements CommandLineRunner {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public Loader(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @Override
    public void run(String... args) {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleServiceImpl.saveRole(adminRole);
        roleServiceImpl.saveRole(userRole);

        User admin = new User("Erik", "BigBoss", "IT", 450, "admin",
                "admin", Collections.singleton(adminRole));
        User user = new User("Malik", "Bakhriddinov", "IT", 350, "user",
                "user", Collections.singleton(userRole));
        User malik = new User("Malik", "Tregulov", "IT", 250, "malik",
                "user", Collections.singleton(userRole));
        User olya = new User("Olya", "SmallBoss", "IT", 400, "olya",
                "admin", Collections.singleton(adminRole));
        User ivan = new User("Ivan", "Milf", "IT", 250, "ivan",
                "user", Collections.singleton(userRole));


        userServiceImpl.saveUser(admin);
        userServiceImpl.saveUser(malik);
        userServiceImpl.saveUser(olya);
        userServiceImpl.saveUser(ivan);
        userServiceImpl.saveUser(user);

    }
}
