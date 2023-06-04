package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String userList(Model model, Principal principal) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user_roles", roleService.allRoles());
        model.addAttribute("currentUser", userService.loadUserByUsername(principal.getName()));
        return "user-list";
    }

    @GetMapping("/addNewUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("user_roles", roleService.allRoles());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/edit-user/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user_roles", roleService.allRoles());
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PatchMapping(value = "/edit-user")
    public String updateUser(@ModelAttribute("user") User user) {
//        if (user.getPassword() == null || user.getRoles() == null ){
//            user.setPassword(userService.findUserById(user.getId()).getPassword());
//            user.setRoles(userService.findUserById(user.getId()).getRoles());
//        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
