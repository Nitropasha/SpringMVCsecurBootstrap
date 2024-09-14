package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

//@Controller
//class LoginController {
//    @GetMapping("/login")
//    String login() {
//        return "login";
//    }
//}
@Controller
public class LoginController {

    // Метод для отображения страницы логина
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Возвращает шаблон "login.html"
    }

    // Этот метод можно добавить, если нужно перенаправлять после успешного логина
//    @GetMapping("/home")
//    public String homePage(Principal principal, Model model) {
//        // Получаем информацию о текущем пользователе
//        String email = principal.getName();
//        model.addAttribute("email", email);
//        return "home"; // Возвращает шаблон "home.html"
//    }

//    // Обработка ошибок авторизации
//    @GetMapping("/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }
}