package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.entities.furnituresEntities.Livraison;

import java.util.List;

public interface ILivraisonService {
    public void addLivraionPrice(Float livraisonPrice);
    public void deleteLivraionPriceById(Long livraisonId);
    public List<Livraison> getAllLivraionPrice();
}
