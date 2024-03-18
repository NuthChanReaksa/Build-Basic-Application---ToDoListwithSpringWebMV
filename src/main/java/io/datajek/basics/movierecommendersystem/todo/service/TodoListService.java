package io.datajek.basics.movierecommendersystem.todo.service;


import io.datajek.basics.movierecommendersystem.todo.model.Todo;

import java.util.List;

public interface TodoListService {
    List<Todo> getAllTodos();

    List<Todo> searchTodoByTask(String task);

    List<Todo> searchTodoByTaskAndIsDone(String task, String  isDone);

    List<Todo> searchTodoByIsDone(String isDone);

    Todo getTodoById(int id);

    void addTodo(Todo todo);

    void updateTodo(int id, Todo todoDetails);

    void deleteTodoById(int id);
}
