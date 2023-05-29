package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.test.Test;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    private Test test;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String userList(Model model, Principal principal) {
        test.CreateDrop();
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user_roles", roleService.allRoles());
        model.addAttribute("currentUser", userService.loadUserByUsername(principal.getName()));
        return "user-list";
    }

    @GetMapping("/add-new-user")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("user_roles", roleService.allRoles());
        return "user-create";
    }

    @PostMapping("/add-new-user")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit-user/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user_roles", roleService.allRoles());
        model.addAttribute("currentUser", user);
        return "user-create";
    }

    @PostMapping("/edit-user")
    public String editUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("currentUser", user);
        return "delete-user";
    }

    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
