package io.datajek.basics.movierecommendersystem.todo.service;

import io.datajek.basics.movierecommendersystem.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static io.datajek.basics.movierecommendersystem.todo.model.Todo.nextId;

@Service
public class TodoListServiceImpl implements TodoListService {
    LocalDateTime now = LocalDateTime.now();
    List<Todo> todoList = new ArrayList<>() {{
        add(new Todo(++nextId, "Create Spring Boot Application", "Create Spring Boot Application with unit testing", "yes", now));
        add(new Todo(++nextId, "Create Spring Project Packages", "Create packages for controllers, services, models, and repositories", "no", now));
        add(new Todo(++nextId, "Create Todo Model", "Create a Todo model with fields: id, task, description, isDone, createdAt", "no", now));
    }};

    @Override
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @Override
    public List<Todo> searchTodoByTask(String task) {
        //ignore Upper and lower case
        return getAllTodos().stream()
                .filter(todo -> todo.getTask().toLowerCase(Locale.ROOT).contains(task.toLowerCase(Locale.ROOT)))
                .toList();

    }

    @Override
    public List<Todo> searchTodoByTaskAndIsDone(String task, String isDone) {
        return getAllTodos().stream()
                .filter(todo -> todo.getTask().toLowerCase(Locale.ROOT).contains(task.toLowerCase(Locale.ROOT)) && todo.getIsDone().contains(isDone))
                .toList();
    }


    @Override
    public List<Todo> searchTodoByIsDone(String isDone) {
        return getAllTodos().stream()
                .filter(todo -> todo.getIsDone().toLowerCase(Locale.ROOT).contains(isDone))
                .toList();
    }

    @Override
    public Todo getTodoById(int id) {
        return todoList.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addTodo(Todo todo) {
        todoList.add(todo);
    }

    @Override
    public void updateTodo(int id, Todo todoDetails) {
        Todo todo = getTodoById(id);
        todo.setTask(todoDetails.getTask());
        todo.setDescription(todoDetails.getDescription());
        todo.setIsDone(todoDetails.getIsDone());
    }

    @Override
    public void deleteTodoById(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
    }
}
