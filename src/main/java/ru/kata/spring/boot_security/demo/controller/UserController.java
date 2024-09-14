package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserServesTwo;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    // Login form

    @RequestMapping(value = "/admin")
    public String printWelcome(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        // Добавляем пустого пользователя для формы в модальном окне
        model.addAttribute("user", new User());
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);

        return "users";
    }

    @RequestMapping(value = "/user")
    public String printUserPage(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        System.err.println(principal.getName());

        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info2";
    }

    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info";
    }

    @RequestMapping(value = "/admin/saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Если есть ошибки, вернуться на ту же страницу
            return "users";
        } else {
            // Если ID пользователя существует, обновляем данные, иначе создаем нового
            userService.saveUser(user);
            return "redirect:/admin";
        }
    }


    @RequestMapping("user/saveUser2")
    public String saveEmployee2(@Valid @ModelAttribute("user") User user,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user-info";
        } else {
            userService.roleNull(user);
            userService.saveUser(user);
            return "redirect:/user";
        }
    }

    @RequestMapping("admin/updateInfo")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-info";
    }

    @RequestMapping("admin/delete")
    public String deleteEmployee(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";

    }

}