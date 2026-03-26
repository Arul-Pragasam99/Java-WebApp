package com.webapp.controller;

import com.webapp.model.Task;
import com.webapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("totalCount", taskService.countTotal());
        model.addAttribute("pendingCount", taskService.countPending());
        model.addAttribute("completedCount", taskService.countCompleted());
        model.addAttribute("newTask", new Task());
        return "index";
    }

    @PostMapping("/tasks")
    public String createTask(@Valid @ModelAttribute("newTask") Task task,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasks());
            model.addAttribute("totalCount", taskService.countTotal());
            model.addAttribute("pendingCount", taskService.countPending());
            model.addAttribute("completedCount", taskService.countCompleted());
            return "index";
        }
        taskService.saveTask(task);
        redirectAttrs.addFlashAttribute("successMsg", "Task added successfully!");
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggleTask(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        taskService.toggleTask(id);
        redirectAttrs.addFlashAttribute("successMsg", "Task status updated!");
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        return taskService.getTaskById(id).map(task -> {
            model.addAttribute("task", task);
            return "edit";
        }).orElse("redirect:/");
    }

    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id,
                             @Valid @ModelAttribute("task") Task task,
                             BindingResult result,
                             RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "edit";
        }
        task.setId(id);
        taskService.saveTask(task);
        redirectAttrs.addFlashAttribute("successMsg", "Task updated successfully!");
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        taskService.deleteTask(id);
        redirectAttrs.addFlashAttribute("successMsg", "Task deleted!");
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
