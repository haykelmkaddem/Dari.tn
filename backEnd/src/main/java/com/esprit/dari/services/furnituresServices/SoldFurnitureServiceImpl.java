package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.SoldFurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldFurnitureServiceImpl implements ISoldFurnitureService{

    @Autowired
    SoldFurnitureRepository soldFurnitureRepository;
}
