package com.esprit.dari.dao.furnituresRepository;

import com.esprit.dari.entities.furnituresEntities.Furniture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends CrudRepository<Furniture,Long> {

    @Query("SELECT f FROM Furniture f WHERE f.furnitureId=:furnitureId")
    public Furniture getFurnitureById(@Param("furnitureId")Long furnitureId);
}
