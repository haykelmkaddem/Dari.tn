package com.esprit.dari.controller.furnituresController;

import com.esprit.dari.entities.furnituresEntities.Category;
import com.esprit.dari.entities.furnituresEntities.Furniture;
import com.esprit.dari.services.furnituresServices.IFurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/furnitures")
public class FurnitureRestController {

    @Autowired
    IFurnitureService iFurnitureService;


    @PostMapping("/addFurniture")
    @ResponseBody
    public Long addFurniture(@RequestBody Furniture furniture){
        iFurnitureService.ajouterFurniture(furniture);
        return furniture.getFurnitureId();
    }
    @DeleteMapping("/deleteFurniture/{furnitureId}")
    @ResponseBody
    public void deleteFurniture(@PathVariable("furnitureId") Long furnitureId){
        iFurnitureService.deleteFurnitureById(furnitureId);
    }

    @GetMapping(value = "/getAllCFurniture")
    @ResponseBody
    public List<Furniture> findFurnitures(){
        return iFurnitureService.getAllFurniture();
    }

    @PutMapping("/modifierFurniture/{furnitureId}")
    @ResponseBody
    public Long modifierFurniture(@PathVariable("furnitureId") Long id,@RequestBody Furniture furniture){
        iFurnitureService.modifiererFurniture(id,furniture);
        return furniture.getFurnitureId();
    }

    @GetMapping(value = "/getFurnitureById/{furnitureId}")
    @ResponseBody
    public Furniture findFurnitureById(@PathVariable("furnitureId")Long id){
        return iFurnitureService.getFurnitureById(id);
    }
}
