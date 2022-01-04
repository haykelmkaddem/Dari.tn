package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.GiftPointsRepository;
import com.esprit.dari.dao.userrepository.UserDariRepository;
import com.esprit.dari.entities.furnituresEntities.GiftPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GiftPointsServiceImpl implements IGiftPointsService{

    @Autowired
    GiftPointsRepository giftPointsRepository;
    @Autowired
    UserDariRepository userDariRepository;


    @Override
    public Long transferGiftPoints(Long userDariId,int points) {
        String etat = "Ready";
        // UserDari userDari = userDariRepository.findById(userDariId).get();
        GiftPoints giftPoints = giftPointsRepository.getAllGiftPointsByUserAndEtat(etat,userDariId);

        int totalePoints = giftPoints.getPoints();
        if (totalePoints >= points){
            Float f = points / 15f ;
            giftPoints.setValue(f);

            LocalDate today = LocalDate.now();
            giftPoints.setBegin_date(today);
            LocalDate afterMonth = LocalDate.now().plusMonths(1);
            giftPoints.setEnd_date(afterMonth);

            int max= 99999999;
            int min = 10000000 ;

            Boolean fortest = true;
            String generatedCode = "";
            while (fortest == true) {
                int generatedNumber = (int) ((Math.random() * (max - min)) + min);
                String generatedCodetest = "HAY" + generatedNumber + "TN";
                fortest = verifCode(generatedCodetest);
                if (fortest == false){
                    generatedCode = generatedCodetest;
                }
            }
            giftPoints.setCode(generatedCode);
            giftPoints.setEtat("ToUser");
            giftPointsRepository.save(giftPoints);

            GiftPoints giftPoints1 = new GiftPoints();
            giftPoints1.setPoints(giftPoints.getPoints() - points);
            giftPoints1.setEtat("Ready");
            giftPoints1.setUsers(giftPoints.getUsers());
            giftPointsRepository.save(giftPoints1);

            giftPoints.setPoints(points);
            giftPointsRepository.save(giftPoints);

        } else {
            System.out.println("vérifier vos points");
        }

        return giftPoints.getGiftpointsId();
    }

    @Override
    public void deleteGiftPointsById(Long giftPointsId) {
        giftPointsRepository.deleteById(giftPointsId);

    }

    @Override
    public List<GiftPoints> getAllGiftPoints() {
        return (List<GiftPoints>) giftPointsRepository.findAll();
    }

    @Override
    public Boolean verifCode(String code) {
        Boolean test = false;
        for (GiftPoints g : getAllGiftPoints()){
            if (code == g.getCode()){
                test = true;
            }
        }
        return test;
    }

    @Override
    public Long modifiererGiftPoints(Long id, GiftPoints giftPoints) {
        return null;
    }

    @Override
    public GiftPoints getGiftPointsById(Long giftPointsId) {
        GiftPoints giftPoints = giftPointsRepository.findById(giftPointsId).get();
        return giftPoints;
    }
}
