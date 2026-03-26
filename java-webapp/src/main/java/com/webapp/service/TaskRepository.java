package com.webapp.service;

import com.webapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCompletedFalseOrderByCreatedAtDesc();

    List<Task> findByCompletedTrueOrderByCreatedAtDesc();

    List<Task> findAllByOrderByCreatedAtDesc();

    @Query("SELECT COUNT(t) FROM Task t WHERE t.completed = false")
    long countPendingTasks();

    @Query("SELECT COUNT(t) FROM Task t WHERE t.completed = true")
    long countCompletedTasks();
}
