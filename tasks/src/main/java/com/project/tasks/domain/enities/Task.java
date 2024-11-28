package com.project.tasks.domain.enities;

import com.project.tasks.domain.enums.TaskPriority;
import com.project.tasks.domain.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDateTime dueDate;

    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false)
    private TaskPriority taskPriority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;

}
