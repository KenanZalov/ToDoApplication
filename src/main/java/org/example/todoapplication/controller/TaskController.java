package org.example.todoapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.todoapplication.dto.request.TaskRequestDto;
import org.example.todoapplication.dto.response.TaskResponseDto;
import org.example.todoapplication.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/addTask")
    public String addTask(@RequestBody TaskRequestDto taskRequestDto) {
        return taskService.addTask(taskRequestDto);
    }

    @GetMapping("/getAllTasks")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/updateTask/{id}")
    public String updateTask(@RequestBody TaskRequestDto taskRequestDto,
                             @PathVariable Long id) {
        return taskService.updateTask(id, taskRequestDto);
    }

    @DeleteMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/filter")
    public List<TaskResponseDto> filterTasksByCategory(@RequestParam Long categoryId) {
        return taskService.filterTasksByCategory(categoryId);
    }
}
