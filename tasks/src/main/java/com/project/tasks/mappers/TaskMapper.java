package com.project.tasks.mappers;

import com.project.tasks.domain.dtos.TaskDto;
import com.project.tasks.domain.enities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
