package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.CategoryRepository;
import com.esprit.dari.entities.furnituresEntities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Long ajouterCategory(Category category) {
        categoryRepository.save(category);
        return category.getCategoryId();
    }


    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Long modifCategory(Category category) {
        categoryRepository.save(category);
        return category.getCategoryId();
    }

    @Override
    public Long modifiererCategory(Long id, Category category) {
        Category category1 = categoryRepository.findById(id).get();
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        categoryRepository.save(category1);
        return category1.getCategoryId();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return category;
    }

}
