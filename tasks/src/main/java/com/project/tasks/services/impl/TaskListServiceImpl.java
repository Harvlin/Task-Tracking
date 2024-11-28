package com.project.tasks.services.impl;

import com.project.tasks.domain.enities.TaskList;
import com.project.tasks.repositories.TaskListRepository;
import com.project.tasks.services.TaskListService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

}
