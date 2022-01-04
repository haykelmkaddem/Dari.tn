package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.BasketRepository;
import com.esprit.dari.dao.furnituresRepository.FurnitureRepository;
import com.esprit.dari.dao.userrepository.UserDariRepository;
import com.esprit.dari.entities.furnituresEntities.Basket;
import com.esprit.dari.entities.furnituresEntities.Furniture;
import com.esprit.dari.entities.userentity.UserDari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements IBasketService{

    @Autowired
    BasketRepository basketRepository;
    @Autowired
    FurnitureRepository furnitureRepository;
    @Autowired
    UserDariRepository userDariRepository;


    @Override
    public Long ajouterBasket(int quantity, Long userDariId, Long furnitureId) {
        Long basketID = 0L;
        Furniture furniture = furnitureRepository.findById(furnitureId).get();
        UserDari userDari = userDariRepository.findById(userDariId).get();
        if (verifierFournitureInBasket(userDariId , furnitureId) == false){
            Basket basket= new Basket();
            basket.setQuantity(quantity);
            basket.setFurnitures(furniture);
            basket.setUsers(userDari);
            basketRepository.save(basket);
            basketID = basket.getBasketId();
        } else if (verifierFournitureInBasket(userDariId , furnitureId) == true ){
            Basket basket = basketRepository.getBasketByUserDariAndFurnitureOne(userDariId , furniture);
            int quantitybasket = basket.getQuantity();
            basket.setQuantity(quantity + quantitybasket);
            basketRepository.save(basket);
            basketID = basket.getBasketId();
        }


        return basketID;
    }

    @Override
    public void deleteBasketById(Long basketId) {
        basketRepository.deleteById(basketId);
    }

    @Override
    public List<Basket> getAllBasket() {
        return (List<Basket>) basketRepository.findAll();
    }

    @Override
    public Boolean verifierFournitureInBasket(Long userDariId, Long furnitureId ) {
        Boolean verifFurniture = false;
        Furniture furniture = furnitureRepository.findById(furnitureId).get();
        UserDari userDari = userDariRepository.findById(userDariId).get();

        List<Basket> basketList = basketRepository.getBasketByUserDariAndFurniture(userDariId, furniture);
        for (Basket basket : basketList){
            if (basket.getFurnitures()==furniture){
                verifFurniture = true;
            }
        }
        return verifFurniture;
    }

    @Override
    public Long modifiererBasket(Long id, Basket basket) {
        Basket basket1 = basketRepository.findById(id).get();
        basket1.setQuantity(basket.getQuantity());
        basketRepository.save(basket1);
        return basket1.getBasketId();
    }

    @Override
    public Basket getBasketById(Long basketId) {
        Basket basket = basketRepository.findById(basketId).get();
        return basket;
    }
}
