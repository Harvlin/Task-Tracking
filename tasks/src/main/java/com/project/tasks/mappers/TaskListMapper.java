package com.project.tasks.mappers;

import com.project.tasks.domain.dtos.TaskListDto;
import com.project.tasks.domain.enities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}
