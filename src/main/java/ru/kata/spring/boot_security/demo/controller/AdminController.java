package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.RoleDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

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
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role") Long roleId) {
        Role role = roleService.findRoleById(roleId);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

//    @PostMapping("/user-create")
//    public String addUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

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
    public String deleteUserForm(@PathVariable("id") Long id, Model model) {
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
