package fr.simplon.thymeleaf.controller;

import java.util.InputMismatchException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.simplon.thymeleaf.message.UserLoginMapping;
import fr.simplon.thymeleaf.message.UserRegisterMapping;
import fr.simplon.thymeleaf.model.User;
import fr.simplon.thymeleaf.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Controller
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(
        @RequestParam(name="error", required=false) String error,
        @RequestParam(name="status", required=false) String status,
        Model model
    ) {
        model.addAttribute("error", error);
        model.addAttribute("status", status);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginMapping userMapping) {
        Optional<User> maybeUser = userRepository
                .findByUsernameAndPassword(
                        userMapping.getUsername(),
                        userMapping.getPassword()
                );

        if (maybeUser.isEmpty()) {
            return "redirect:/login?error=invalidCredentials";
        }

        User user = maybeUser.get();

        return "redirect:/" + user.getId();
    }

    @GetMapping("/register")
    public String register(
        @RequestParam(name="error", required=false) String error,
        Model model
    ) {
        model.addAttribute("error", error);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterMapping userMapping) {
        if (!userMapping.getPassword().equals(userMapping.getPasswordConfirm())) {
            return "redirect:/register?error=inputMismatch";
        }

        User user = User.builder()
                .username(userMapping.getUsername())
                .email(userMapping.getEmail())
                .password(userMapping.getPassword())
                .build();

        userRepository.save(user);

        return "redirect:/login?status=registrationComplete";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/{id}")
    public String home(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        model.addAttribute("currentUser", user);
        return "index";
    }

    @GetMapping("/admin/{id}")
    public String admin(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        model.addAttribute("currentUser", user);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

}
