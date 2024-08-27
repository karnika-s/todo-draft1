package com.example.todo_2.controllers;

//import com.example.todoapp.model.User;
//import com.example.todoapp.service.CustomUserDetailsService;
import com.example.todo_2.model.User;
import com.example.todo_2.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
        public String registerPage(@RequestParam(value = "error", required = false) String error, Model model) {
            if (error != null) {
                model.addAttribute("error", "Registration failed. Please try again.");
            }
            return "register";
        }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userDetailsService.save(user);
        return "redirect:/todos"; // Redirect to the todo list page after registration
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}