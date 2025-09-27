package com.example.bth09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bth09.entity.Category;
import com.example.bth09.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(String name, String images) {
        Category category = new Category();
        category.setName(name);
        category.setImages(images);
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, String name, String images) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            if (name != null) {
                category.setName(name);
            }
            if (images != null) {
                category.setImages(images);
            }
            return categoryRepository.save(category);
        }
        return null;
    }

    public boolean deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
