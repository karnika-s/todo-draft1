package com.example.todo_2.repository;

//import com.example.todoapp.model.User;
import com.example.todo_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}