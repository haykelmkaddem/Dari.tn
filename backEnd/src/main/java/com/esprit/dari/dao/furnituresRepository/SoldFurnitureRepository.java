package com.esprit.dari.dao.furnituresRepository;

import com.esprit.dari.entities.furnituresEntities.SoldFurniture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SoldFurnitureRepository extends CrudRepository<SoldFurniture, Long> {

    @Query("select DISTINCT s from SoldFurniture s "
            + "join s.commands c "
            + "where c.commandId=:commandId "
    )
    public List<SoldFurniture> getAllSoldFurnitureByCommands(@Param("commandId") Long commandId);
}
