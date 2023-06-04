package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String userList(Model model, Principal principal) {
        List<User> allUsers = userService.allUsers();
        model.addAttribute("users", allUsers);
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
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user_roles", roleService.allRoles());
            return "user-create";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/userUpdate")
    public String editUserForm(@RequestParam("userId") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("user_roles", roleService.allRoles());

        return "edit-user";
    }

    @PatchMapping(value = "/userUpd")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (!userService.updateUser(user, bindingResult)) {
            model.addAttribute("user_roles", roleService.allRoles());
            return "edit-user";
        }

        return "redirect:/admin";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
