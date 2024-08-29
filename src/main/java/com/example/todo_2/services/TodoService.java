package com.example.todo_2.services;

//import com.example.todoapp.model.Todo;
//import com.example.todoapp.model.User;
//import com.example.todoapp.repository.TodoRepository;
import com.example.todo_2.model.Todo;
import com.example.todo_2.model.User;
import com.example.todo_2.repository.TodoRepository;
import com.example.todo_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;



    // Update method to find todos by user
//    public Optional<Todo> findByUser(User user) {
//        return todoRepository.findByUserId(user.getId());
//    }

    public Todo save(Todo item)
    {
        if(item.getId()  == null)
        {
            item.setCreatedAt(Instant.now());
        }
        item.setUpdatedAt(Instant.now());
        return todoRepository.save(item);
    }
    /**
     * Save a new or existing todo.
     *
     * @param todoItem the todo to save
     * @return the saved todo
     */
//    public Todo save(Todo todoItem) {
//        return todoRepository.save(todoItem);
//    }

    /**
     * Find all todos for a specific user.
     *
     * @param user the user to find todos for
     * @return the list of todos
     */
//    public List<Todo> findByUser(User user) {
//        return todoRepository.findByUser(user);
//    }

    /**
     * Find a todo by its ID.
     *
//     * @param id the ID of the todo
     * @return the todo if found, otherwise empty
     */

    //method used otherwise
    public Todo findById(Long id) {
        Optional<Todo> result = todoRepository.findById(id);
        return result.orElse(null);
    }


    //method used with session
//    public List<Todo> findByUserId(Long userId) {
//        return todoRepository.findByUserId(userId);
//    }
//    /**
//     * Delete a todo by its ID.
//     *
//     * @param id the ID of the todo to delete
//     */
public void deleteById(Long id) {
    todoRepository.deleteById(id);
}

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
