package org.example.todoapplication.dto.request;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String taskTitle;
    private String description;
    private String taskStatus;
    private Long categoryId;
}
