package com.webapp.service;

import com.webapp.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Task> getPendingTasks() {
        return taskRepository.findByCompletedFalseOrderByCreatedAtDesc();
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrueOrderByCreatedAtDesc();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void toggleTask(Long id) {
        taskRepository.findById(id).ifPresent(task -> {
            task.setCompleted(!task.isCompleted());
            taskRepository.save(task);
        });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public long countPending() {
        return taskRepository.countPendingTasks();
    }

    public long countCompleted() {
        return taskRepository.countCompletedTasks();
    }

    public long countTotal() {
        return taskRepository.count();
    }
}
