package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.LivraisonRepository;
import com.esprit.dari.entities.furnituresEntities.Livraison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonServiceImpl implements ILivraisonService{


    @Autowired
    LivraisonRepository livraisonRepository;


    @Override
    public void addLivraionPrice(Float livraisonPrice) {
        if (livraisonRepository.countLivraison() == 1){
            Livraison livraison = livraisonRepository.findLivraison();
            livraison.setKiloPrice(livraisonPrice);
            livraisonRepository.save(livraison);
        } else {
            Livraison livraison = new Livraison();
            livraison.setKiloPrice(livraisonPrice);
            livraisonRepository.save(livraison);
        }
    }

    @Override
    public void deleteLivraionPriceById(Long livraisonId) {
        livraisonRepository.deleteById(livraisonId);
    }

    @Override
    public List<Livraison> getAllLivraionPrice() {
        return (List<Livraison>) livraisonRepository.findAll();
    }
}
