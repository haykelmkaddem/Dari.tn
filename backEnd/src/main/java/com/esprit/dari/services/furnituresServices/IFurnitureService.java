package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.entities.furnituresEntities.Category;
import com.esprit.dari.entities.furnituresEntities.Furniture;

import java.util.List;

public interface IFurnitureService {

    public Long ajouterFurniture(Furniture furniture);
    public void deleteFurnitureById(Long furnitureId);
    public List<Furniture> getAllFurniture();
    public Long modifiererFurniture(Long id, Furniture furniture);
    public Furniture getFurnitureById(Long furnitureId);
}
