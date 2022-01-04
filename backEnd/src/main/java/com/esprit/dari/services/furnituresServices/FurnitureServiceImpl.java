package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.FurnitureRepository;
import com.esprit.dari.entities.furnituresEntities.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureServiceImpl implements IFurnitureService{

    @Autowired
    FurnitureRepository furnitureRepository;

    @Override
    public Long ajouterFurniture(Furniture furniture) {
        furnitureRepository.save(furniture);
        return furniture.getFurnitureId();
    }

    @Override
    public void deleteFurnitureById(Long furnitureId) {
        furnitureRepository.deleteById(furnitureId);

    }

    @Override
    public List<Furniture> getAllFurniture() {
        return (List<Furniture>) furnitureRepository.findAll();

    }

    @Override
    public Long modifiererFurniture(Long id, Furniture furniture) {
        Furniture furniture1 = furnitureRepository.findById(id).get();
        furniture1.setName(furniture.getName());
        furniture1.setDescription(furniture.getDescription());
        furniture1.setPrice(furniture.getPrice());
        furniture1.setHeight(furniture.getHeight());
        furniture1.setStock(furniture.getStock());
        furniture1.setWeight(furniture.getWeight());
        furniture1.setWidth(furniture.getWidth());
        furniture1.setCategory(furniture.getCategory());
        furnitureRepository.save(furniture1);
        return furniture1.getFurnitureId();
    }

    @Override
    public Furniture getFurnitureById(Long furnitureId) {
        Furniture furniture = furnitureRepository.findById(furnitureId).get();
        return furniture;
    }
}
