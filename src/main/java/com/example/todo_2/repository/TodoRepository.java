package com.example.todo_2.repository;

//import com.example.todoapp.model.Todo;
//import com.example.todoapp.model.User;
import com.example.todo_2.model.Todo;
import com.example.todo_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
//    List<Todo> findByUser(User user);
}
