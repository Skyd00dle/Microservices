package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String adminPage (Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam List<Integer> roleIds) {
        user.setRoles(roleService.getRolesByIds(roleIds));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUserForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }


    @PostMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id,
                           @ModelAttribute("user") User user,
                           @RequestParam(name = "roleIds", required = false) List<Integer> roleIds) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            if (roleIds != null) {
                existingUser.setRoles(roleService.getRolesByIds(roleIds));
            }
            userService.updateUser(existingUser);
        }
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}

