package ru.kata.spring.boot_security.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;



@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserInfo(Principal principal, ModelMap model) {
        User user = userService.loadUserByUsername(principal.getName());
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : user.getAuthorities()) {
            roles.append(authority.getAuthority());
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", roles.toString());
        return "user";
    }

//    @GetMapping("/")
//    public String findAll(Model model) {
////        List<User> allUsers = userService.listUser();
////        model.addAttribute("allUsers", allUsers);
//        return "user-list";
//    }

//    @GetMapping("/addNewUser")
//    public String createUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "user-create";
//    }
//
//    @PostMapping("/user-create")
//    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            return "user-create";
//        }
//        userService.add(user);
//        return "redirect:/";
//    }
//
//    @DeleteMapping("/deleteUser")
//    public String deleteUser(@RequestParam("userId") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/";
//    }
//
//    @PatchMapping("/update-info")
//    public String updateUserForm(@RequestParam("userId") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "user-create";
//    }

}
