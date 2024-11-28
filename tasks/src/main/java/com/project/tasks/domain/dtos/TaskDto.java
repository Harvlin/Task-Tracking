package com.project.tasks.domain.dtos;

import com.project.tasks.domain.enums.TaskPriority;
import com.project.tasks.domain.enums.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
