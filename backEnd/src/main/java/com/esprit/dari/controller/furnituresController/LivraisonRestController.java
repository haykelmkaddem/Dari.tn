package com.esprit.dari.controller.furnituresController;

import com.esprit.dari.entities.furnituresEntities.Livraison;
import com.esprit.dari.services.furnituresServices.ILivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/furnitures")
public class LivraisonRestController {

    @Autowired
    ILivraisonService iLivraisonService;


    @PostMapping("/livraisonPrice/{livraisonPrice}")
    @ResponseBody
    public void updateLivraisonPrice(@PathVariable("livraisonPrice") Float livraisonPrice){
        iLivraisonService.addLivraionPrice(livraisonPrice);
    }
    @GetMapping(value = "/getAllLivraison")
    @ResponseBody
    public List<Livraison> findAllLivraison(){
        return iLivraisonService.getAllLivraionPrice();
    }


    @DeleteMapping("/deletelivraisonPrice/{livraisonPriceId}")
    @ResponseBody
    public void deleteLivraison(@PathVariable("livraisonPriceId") Long livraisonPriceId){
        iLivraisonService.deleteLivraionPriceById(livraisonPriceId);
    }
}
