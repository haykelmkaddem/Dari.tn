package com.esprit.dari.controller.furnituresController;

import com.esprit.dari.entities.furnituresEntities.Category;
import com.esprit.dari.services.furnituresServices.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/furnitures")
public class CategoryRestController {

    @Autowired
    ICategoryService iCategoryService;

    @PostMapping("/addCategory")
    @ResponseBody
    public Long addCategory(@RequestBody Category category){
        iCategoryService.ajouterCategory(category);
        return category.getCategoryId();
    }
    @GetMapping(value = "/getAllCategory")
    @ResponseBody
    public List<Category> findAllCategory(){
        return iCategoryService.getAllCategory();
    }


    @DeleteMapping("/deleteCategory/{categoryId}")
    @ResponseBody
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        iCategoryService.deleteCategoryById(categoryId);
    }

    @PutMapping("/modifCategory")
    @ResponseBody
    public Long modifCategory(@RequestBody Category category){
        iCategoryService.modifCategory(category);
        return category.getCategoryId();
    }

    @PutMapping("/modifierCategory/{categoryId}")
    @ResponseBody
    public Long modifierCategory(@PathVariable("categoryId") Long id,@RequestBody Category category){
        iCategoryService.modifiererCategory(id,category);
        return category.getCategoryId();
    }

    @GetMapping(value = "/getCategoryById/{categoryId}")
    @ResponseBody
    public Category findCategoryById(@PathVariable("categoryId")Long id){
        return iCategoryService.getCategoryById(id);
    }


}
