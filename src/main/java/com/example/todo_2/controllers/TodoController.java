package com.example.todo_2.controllers;

//import com.example.todoapp.model.Todo;
//import com.example.todoapp.model.User;
//import com.example.todoapp.service.TodoService;
//import com.example.todoapp.service.CustomUserDetailsService;
import com.example.todo_2.model.Todo;
import com.example.todo_2.services.CustomUserDetailsService;
import com.example.todo_2.services.TodoService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private CustomUserDetailsService userDetailsService;


//    public String listTodos(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        User user = userDetailsService.findByEmail(username);
//        model.addAttribute("todos", todoService.findByUser(user));
//        return "todo-list";
//    }

//    used otherwise
    @GetMapping("/all")
    public String listTodos(Model model) {
        System.out.println(" in todos all");
        List<Todo> todoItems = todoService.findAll();
        model.addAttribute("todoItems", todoItems);
        return "todo-list";
    }

//used with session
//@GetMapping("/all")
//public String listTodos(HttpSession session, Model model) {
//    Long userId = (Long) session.getAttribute("userId");
//    Long Id = (Long) session.getAttribute("Id");
//    System.out.println("User ID from todo session: " + userId);  // Debug output
//    System.out.println("user if from user" + Id);
//
//    if (userId == null) {
//        return "redirect:/login";  // Redirect to login if userId is not found in the session
//    }
//
//    List<Todo> todoItems = todoService.findByUserId(userId);
//    if (todoItems == null || todoItems.isEmpty()) {
//        System.out.println("No todos found for user ID: " + userId);  // Debug output
//    }
//
//    model.addAttribute("todoItems", todoItems);
//    return "todo-list";
//}

    // Method to show the form for creating a new Todo item
    @GetMapping("/create-todo")
    public String showCreateForm(Model model) {
        model.addAttribute("todoItem", new Todo()); // Adding an empty TodoItem object to the model
        return "new-todo-item";  // Return the Thymeleaf template name
    }

    // Method to handle form submission for creating a new Todo item
//    @PostMapping("/topost")
//    public String createTodo(@ModelAttribute("todoItem") Todo todoItem)
////        public String addTodoItem(Todo todoItem)
//    {
//        // Logic to save the TodoItem to the database goes here
//        todoService.save(todoItem);
//        // After saving, redirect to the list of all Todo items
////        return "redirect:/todos/all";
//        return "redirect:/todos/all";
//    }

    @PostMapping("/topost")
    public String createTodoItem(@Valid Todo todoItem, BindingResult result, Model model)
    {
        //from se aane wale data ko entity me set kardenge
        Todo item = new Todo();
        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        todoService.save(todoItem);
        return "redirect:/todos/all";  //back to home
    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") Long id , Model model) {
//        Todo item = todoService
//                .findById(id)    //ek type se exception handle karliya
//                .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + " notfound"));
//        //apart from this older versions made optional object
//
//        model.addAttribute("todo", item);  //wapas usi task pe jana h na isliye model
//        return "edit-todo-item";
//    }



    //method used otherwise
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid todo id: " + id));

        model.addAttribute("todo", todo);
        return "edit_todo";  // The name of your Thymeleaf template
    }


//method used with session
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
//        Long userId = (Long) session.getAttribute("userId");
//        if (userId == null) {
//            return "error";  // Handle the case where userId is not found in the session
//        }
//
//        List<Todo> todoItems = todoService.findByUserId(userId);
//        Optional<Todo> optionalTodo = todoItems.stream().filter(todo -> todo.getId().equals(id)).findFirst();
//        if (optionalTodo.isEmpty()) {
//            return "error";  // Ensure the todo belongs to the current user
//        }
//        model.addAttribute("todo", optionalTodo.get());
//        return "edit_todo";
//    }

    //method used otherwise
    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute("todo") Todo todo) {
        // Find the existing Todo by id
        Todo existingTodo = todoService.findById(id);
//            .orElseThrow(() -> new IllegalArgumentException("Todo item id: " + id + " notfound"));

        // Update fields
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setIsComplete(todo.getIsComplete());
//            existingTodo.setUpdatedAt(LocalDateTime.now());

        // Save the updated Todo
        todoService.save(existingTodo);

        // Redirect to the /todos/all page after update
        return "redirect:/todos/all";
    }

    //method used with session
//    @PostMapping("/edit/{id}")
//    public String updateTodo(@PathVariable Long id, @ModelAttribute("todo") Todo todo, HttpSession session) {
//        Long userId = (Long) session.getAttribute("userId");
//        if (userId == null) {
//            return "error";  // Handle the case where userId is not found in the session
//        }
//
//        List<Todo> todoItems = todoService.findByUserId(userId);
//        Optional<Todo> optionalTodo = todoItems.stream().filter(t -> t.getId().equals(id)).findFirst();
//        if (optionalTodo.isEmpty()) {
//            return "error";  // Redirect to an error page or handle it appropriately
//        }
//
//        // Update fields
//        Todo existingTodo = optionalTodo.get();
//        existingTodo.setDescription(todo.getDescription());
//        existingTodo.setIsComplete(todo.getIsComplete());
//        existingTodo.setUpdatedAt(Instant.now());
//
//        // Save the updated Todo item
//        todoService.save(existingTodo);
//
//        return "redirect:/todos/all";  // Redirect to the list of all Todo items
//    }


    //used otherwise
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id, Model model) {
        todoService.deleteById(id);
        return "redirect:/todos/all";
    }


    //used with session
//    @GetMapping("/delete/{id}")
//    public String deleteTodo(@PathVariable("id") Long id, HttpSession session) {
//        Long userId = (Long) session.getAttribute("userId");
//        if (userId == null) {
//            return "error";  // Handle the case where userId is not found in the session
//        }
//
//        List<Todo> todoItems = todoService.findByUserId(userId);
//        Optional<Todo> optionalTodo = todoItems.stream().filter(todo -> todo.getId().equals(id)).findFirst();
//        if (optionalTodo.isEmpty()) {
//            return "error";  // Redirect to an error page or handle it appropriately
//        }
//
//        // Delete the Todo item
//        todoService.deleteById(id);
//
//        return "redirect:/todos/all";  // Redirect to the list of all Todo items
//    }


//        --------------------------------------------------
//    @GetMapping("/new")
//    public String newTodoForm(Model model) {
//        model.addAttribute("todo", new Todo());
//        return "todo-form";
//    }
//
//    @PostMapping("/save")
//    public String saveTodo(@ModelAttribute Todo todo) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        User user = userDetailsService.findByEmail(username);
////        todo.setUser(user);
//        todoService.save(todo);
////        return "todo-form"
//        return "redirect:/todos";
//    }

//    @GetMapping("/edit/{id}")
//    public String editTodoForm(@PathVariable Long id, Model model) {
//        Todo todo = todoService.findById(id);
//        model.addAttribute("todo", todo);
//        return "todo-form";
//    }

//    @GetMapping("/delete/{id}")
//    public String deleteTodo(@PathVariable Long id) {
//        todoService.deleteById(id);
//        return "redirect:/todos";
//    }
}