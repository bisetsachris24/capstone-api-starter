package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category create(Category category) {
        return categoryRepository.save(category);  // saves to DB and returns the saved category with its new ID
    }

    public Category update(int categoryId, Category category) {
        Category existing = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(category.getName());
        existing.setDescription(category.getDescription());

        return categoryRepository.save(existing);  // saves the updated category
    }

    public void delete(int categoryId) {
        categoryRepository.deleteById(categoryId);  // deletes from DB
    }
}