package com.example.todo_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

//import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description ;

//    @Column(name="is_Complete", nullable =true)
    private String isComplete ; // Default value

//    private String isComplete; // Changed from Boolean to String

    private Instant createdAt;
    private Instant updatedAt;



    //implementing serializable so overriding
    @Override
    public String toString()
    {
        return String.format("TodoItem{id=%d, description='%s', isComplete='%s', createdAt='%s', updatedAt='%s'}",
                id, description,isComplete, createdAt, updatedAt);
    }

    // Getters and Setters
}