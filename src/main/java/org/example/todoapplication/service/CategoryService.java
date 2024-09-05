package org.example.todoapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.todoapplication.dto.request.CategoryRequestDto;
import org.example.todoapplication.dto.response.CategoryResponseDto;
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
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;


    public String addCategory(CategoryRequestDto requestDto) {
        if (categoryRepository.existsByName(requestDto.getCategoryName())) {
            return "Category already exists";
        }
        Category category = modelMapper.map(requestDto, Category.class);
        categoryRepository.save(category);
        return "Category added";
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(c -> modelMapper.map(c, CategoryResponseDto.class))
                .toList();
    }

    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        if (category.getTasks().isEmpty()) {
            categoryRepository.delete(category);
            return "Category deleted";
        } else {
            return "Category has Task";
        }
    }
}

