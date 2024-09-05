package org.example.todoapplication.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.todoapplication.dto.request.CategoryRequestDto;
import org.example.todoapplication.dto.response.CategoryResponseDto;
import org.example.todoapplication.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addCategory(@RequestBody CategoryRequestDto requestDto){
        return categoryService.addCategory(requestDto);
    }

    @GetMapping("/getAllCategories")
    public List<CategoryResponseDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/deleteCategory")
    public String deleteCategory(@RequestParam Long id){
        return categoryService.deleteCategory(id);
    }


}
