package com.training.todo.service;
import com.training.todo.entity.Task;
import com.training.todo.repository.TaskRepository;
import com.training.todo.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStringStatus(String status) {
        return taskRepository.findByStringStatus(status);
    }

    public Task markTaskAsDone(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        task.setCompleted("Yes");
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
