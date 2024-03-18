package io.datajek.basics.movierecommendersystem.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Todo {
    public static int nextId = 0;
    private int id;
    private String task;
    private String description;
    private String  isDone;
    private LocalDateTime createdAt;
    public Todo() {
        this.id = ++nextId;
    }

}
