package com.esprit.dari.controller.furnituresController;

import com.esprit.dari.entities.furnituresEntities.Basket;
import com.esprit.dari.entities.furnituresEntities.Furniture;
import com.esprit.dari.services.furnituresServices.IBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/furnitures")
public class BasketRestController {

    @Autowired
    IBasketService iBasketService;



    @GetMapping("/addBasket/{quantity}/{userDariId}/{furnitureId}")
    @ResponseBody
    public Long addBasket(@PathVariable("quantity") int quantity, @PathVariable("userDariId") Long userDariId, @PathVariable("furnitureId") Long furnitureId){
        return iBasketService.ajouterBasket(quantity, userDariId, furnitureId);
    }


    @DeleteMapping("/deleteBasket/{basketId}")
    @ResponseBody
    public void deleteBasket(@PathVariable("basketId") Long basketId){
        iBasketService.deleteBasketById(basketId);
    }

    @GetMapping(value = "/getAllBasket")
    @ResponseBody
    public List<Basket> findAllBasket(){
        return iBasketService.getAllBasket();
    }

    @PutMapping("/modifierBasket/{basketId}")
    @ResponseBody
    public Long modifierBasket(@PathVariable("basketId") Long id,@RequestBody Basket basket){
        iBasketService.modifiererBasket(id,basket);
        return basket.getBasketId();
    }

    @GetMapping(value = "/getBasketById/{basketId}")
    @ResponseBody
    public Basket findBasketById(@PathVariable("basketId")Long id){
        return iBasketService.getBasketById(id);
    }

}
