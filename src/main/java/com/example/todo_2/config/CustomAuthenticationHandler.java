//package com.example.todo_2.config;
//
//import com.example.todo_2.model.User;
//import com.example.todo_2.services.CustomUserDetailsService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//
//
//
//public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {
//    private final CustomUserDetailsService userDetailsService;
//
//    public CustomAuthenticationHandler(CustomUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
////        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////        String email = userDetails.getUsername(); // Retrieve email from UserDetails
//
//        String email = authentication.getName();
//        User user = userDetailsService.findByEmail(email);
//        Long userId = user.getId();
//        // Find the user using CustomUserDetailsService
//
//
//        // Redirect to /todos/user/{userId}
//        response.sendRedirect("/todos/user/" + userId);
//
////        if (user != null) {
////            // Redirect to the user's todos page
////            response.sendRedirect("/todos/user/" + user.getId());
////        } else {
////            // If user is not found, redirect to a default page or handle the error
////            response.sendRedirect("/auth/login?error");
////        }
//    }
//}
