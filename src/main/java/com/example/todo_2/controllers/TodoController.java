package com.example.todo_2.controllers;

//import com.example.todoapp.model.Todo;
//import com.example.todoapp.model.User;
//import com.example.todoapp.service.TodoService;
//import com.example.todoapp.service.CustomUserDetailsService;
import com.example.todo_2.model.Todo;
import com.example.todo_2.model.User;
import com.example.todo_2.services.CustomUserDetailsService;
import com.example.todo_2.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/all")
    public String listTodos(Model model) {
        System.out.println(" in todos all");
        List<Todo> todoItems = todoService.findAll();
        model.addAttribute("todoItems", todoItems);
        return "todo-list";
    }

//    @PostMapping("/")


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



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid todo id: " + id));

        model.addAttribute("todo", todo);
        return "edit_todo";  // The name of your Thymeleaf template
    }


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

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id, Model model) {
        todoService.deleteById(id);
        return "redirect:/todos/all";
    }



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