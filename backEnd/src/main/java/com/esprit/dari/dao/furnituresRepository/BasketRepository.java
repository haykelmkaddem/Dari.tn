package com.esprit.dari.dao.furnituresRepository;

import com.esprit.dari.entities.furnituresEntities.Basket;
import com.esprit.dari.entities.furnituresEntities.Furniture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends CrudRepository<Basket,Long> {


    @Query("select DISTINCT b from Basket b "
            + "join b.users u "
            + "where u.userId=:userDariId "
    )
    public List<Basket> getAllBasketByUserDari(@Param("userDariId") Long userDariId);

    // @Query("SELECT b FROM Basket b WHERE (b.users=:userDariId) and (b.furnitures=:furnitureId)")



    @Query("select DISTINCT b from Basket b "
            + "join b.users u "
            + "where u.userId=:userDariId and "
            + "b.furnitures=:furnitureId"
    )
    public List<Basket> getBasketByUserDariAndFurniture(@Param("userDariId")Long userDariId,@Param("furnitureId")Furniture furniture );
    // @Query("SELECT b FROM Basket b WHERE (b.users=:userDariId) and (b.furnitures=:furnitureId)")

    @Query("select DISTINCT b from Basket b "
            + "join b.users u "
            + "where u.userId=:userDariId and "
            + "b.furnitures=:furnitureId"
    )
    public Basket getBasketByUserDariAndFurnitureOne(@Param("userDariId")Long userDariId,@Param("furnitureId") Furniture furniture );


}
