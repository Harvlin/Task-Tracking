package com.project.tasks.services.impl;

import com.project.tasks.domain.enities.Task;
import com.project.tasks.domain.enities.TaskList;
import com.project.tasks.domain.enums.TaskPriority;
import com.project.tasks.domain.enums.TaskStatus;
import com.project.tasks.repositories.TaskListRepository;
import com.project.tasks.repositories.TaskRepository;
import com.project.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getTaskPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        LocalDateTime now = LocalDateTime.now();
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(
                () -> new IllegalArgumentException("Invalid task list ID provided")
        );

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null)
            throw new IllegalArgumentException("Task must have an ID");
        if (!Objects.equals(taskId, task.getId()))
            throw new IllegalArgumentException("Task IDs do not match");
        if (task.getTaskPriority() == null)
            throw new IllegalArgumentException("Task must have a valid priority");
        if (task.getStatus() == null)
            throw new IllegalArgumentException("Task must have a valid status");

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(
                () -> new IllegalArgumentException("Task not found")
        );

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setTaskPriority(task.getTaskPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}