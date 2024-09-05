package org.example.todoapplication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todoapplication.dto.request.TaskRequestDto;
import org.example.todoapplication.dto.response.TaskResponseDto;
import org.example.todoapplication.model.Category;
import org.example.todoapplication.model.Task;
import org.example.todoapplication.repository.CategoryRepository;
import org.example.todoapplication.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public String addTask(TaskRequestDto taskRequestDto) {
        if (taskRepository.existsByTitle(taskRequestDto.getTaskTitle())) {
            return "Task already exists";
        }
        Optional<Category> category = categoryRepository.findById(taskRequestDto.getCategoryId());
        if (category.isPresent()) {
            Task task = modelMapper.map(taskRequestDto, Task.class);
            task.setCategory(category.get());
            taskRepository.save(task);
            return "Task created";
        }
        return "Category does not exist";
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks
                .stream()
                .map(task -> modelMapper.map(task, TaskResponseDto.class))
                .toList();
    }


    public String deleteTask(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();

            Category category = task.getCategory();
            if (category != null) {
                category.getTasks().remove(task);
                categoryRepository.save(category);
            }
            taskRepository.delete(task);
            return "Deleted task";
        } else {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
    }

    public List<TaskResponseDto> filterTasksByCategory(Long categoryId) {
        List<Task> tasks = taskRepository.findByCategoryId(categoryId);
        return tasks
                .stream()
                .map(task -> modelMapper.map(task, TaskResponseDto.class))
                .toList();
    }

    public String updateTask(Long taskId, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Category category = categoryRepository.findById(taskRequestDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        task.setCategory(category);
        modelMapper.map(taskRequestDto, task);
        task.setId(taskId);
        taskRepository.save(task);
        return "Task updated";
    }
}
