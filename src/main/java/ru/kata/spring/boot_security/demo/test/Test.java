package ru.kata.spring.boot_security.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Test {
    private UserService userService;

//    @Autowired
//    public void setUserService(UserService userService){
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init(){
//        Role role = new Role("ROLE_ADMIN");
//        Set<Role> adminRole = new HashSet<>();
//        adminRole.add(role);
//
//        User user = new User("admin", "olga", "fox", "test@mail.ru","12345", adminRole);
//        userService.saveUser(user);
//
//
//    }

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void CreateDrop(){

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        Set<Role> hashSet = new HashSet<>();
        Set<Role> hashSet2 = new HashSet<>();
        hashSet.add(role1);
        hashSet2.add(role2);

        User user1 = new User("wappalik", "Malik", "Tregulov", "malik@mail.ru", "12345",hashSet);
//        User user2 = new User("sdfvb", "zfbzdb", "zbzdfb", "svsd@gv.ru", "12345", hashSet2);
//        User user3 = new User("erik", "erik", "Tregulov", "massfvs@mail.ru", "12345", hashSet2);
//        User user4 = new User("gdsdg", "RSGsg", "Tregulov", "zdhzbdfhb2mail.ru", "12345",hashSet2);
//        User user5 = new User("sdgSDG", "fdfgdg", "Tregulov", "dggs@dfg.ru", "12345", hashSet2);

        user1.setRoles(hashSet);
//        user2.setRoles(hashSet2);
//        user3.setRoles(hashSet2);
//        user4.setRoles(hashSet2);
//        user5.setRoles(hashSet2);

        userRepository.save(user1);
//        userRepository.save(user2);
//        userRepository.save(user3);
//        userRepository.save(user4);
//        userRepository.save(user5);

    }
}
