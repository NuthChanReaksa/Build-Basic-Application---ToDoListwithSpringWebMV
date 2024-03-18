package io.datajek.basics.movierecommendersystem.todo.controller;

import io.datajek.basics.movierecommendersystem.todo.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import io.datajek.basics.movierecommendersystem.todo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Repository
public class TodoListController {
    private TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    // Your code here
    @GetMapping("/")
    public String getTodoList(Model model) {
        model.addAttribute("todoList", todoListService.getAllTodos());
        return "index";
    }

    @GetMapping("/todo/search")
    public String searchTodo(@RequestParam(required = false) String task, @RequestParam(required = false) String isDone, Model model) {
        if (task == null && isDone == null) {
            // If no search parameters are provided, redirect to /todo
            return "redirect:/todo";
        }

        // Redirect to /todo with search parameters as query parameters
        StringBuilder redirectUrl = new StringBuilder("redirect:/todo?");
        if (task != null) {
            redirectUrl.append("task=").append(task).append("&");
        }
        if (isDone != null) {
            redirectUrl.append("isDone=").append(isDone).append("&");
        }
        // Remove the trailing "&" if exists
        if (redirectUrl.charAt(redirectUrl.length() - 1) == '&') {
            redirectUrl.deleteCharAt(redirectUrl.length() - 1);
        }
        return redirectUrl.toString();
    }

    @GetMapping("/todo")
    public String getTodoList(@RequestParam(required = false) String task, @RequestParam(required = false) String isDone, Model model) {
        if (task != null || isDone != null) {
            // Search for todo items using the provided parameters
            List<Todo> todoList;
            if (task != null && isDone != null) {
                todoList = todoListService.searchTodoByTaskAndIsDone(task, isDone);
            } else if (task != null) {
                todoList = todoListService.searchTodoByTask(task);
            } else {
                todoList = todoListService.searchTodoByIsDone(isDone);
            }
            model.addAttribute("todoList", todoList);
        } else {
            // If no search parameters are provided, fetch all todo items
            List<Todo> todoList = todoListService.getAllTodos();
            model.addAttribute("todoList", todoList);
        }
        return "index";
    }

    @GetMapping("/todo/add")
    public String addTodo() {
        return "addTodo";
    }

    @PostMapping("/todo/add")
    public String addTodo(Todo todo) {
        todoListService.addTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable("id")int id, Model model) {
        model.addAttribute("todo", todoListService.getTodoById(id));
        return "editTodo";
    }

    @PostMapping("todo/update/{id}")
    public String updateTodo(@PathVariable("id") int id,Todo todoDetails) {
        todoListService.updateTodo(id,todoDetails);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") int id) {

        todoListService.deleteTodoById(id);
        return "redirect:/";

    }
}

