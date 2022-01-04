package com.esprit.dari.services.CreditServices;

import com.esprit.dari.dao.CreditDAO.BankRepository;
import com.esprit.dari.dao.CreditDAO.DemandeCrRepository;
import com.esprit.dari.entities.CreditEntities.Bank;
import com.esprit.dari.entities.CreditEntities.DemandeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public  class DemandeCreditImpl implements IDemandeCreditService {

    @Autowired
    DemandeCrRepository demandeCrRep;
    @Autowired
    BankRepository BankRep;

    @Override
    public int AjouterDemandeC(DemandeCredit demandeC) {

        demandeCrRep.save(demandeC);



        return demandeC.getId();
    }



    @Override
    public DemandeCredit ModifierDemandeC(int idDemande,DemandeCredit dc) {

       DemandeCredit demandeC=demandeCrRep.findById(idDemande).get();
        demandeC.setAge(dc.getAge());
        demandeC.setDuree(dc.getDuree());
        demandeC.setMontantDemande(dc.getMontantDemande());
        demandeC.setProfession(dc.getProfession());
        demandeC.setSalaire(dc.getSalaire());

demandeCrRep.save(demandeC);
return demandeC;

    }

    @Override
    public List<DemandeCredit> getAllDemandes() {
List<DemandeCredit> demands= (List<DemandeCredit>) demandeCrRep.findAll();
  return demands;
    }

    @Override
    public void SupprimerDemandeC(int IdDemande) {
        demandeCrRep.delete(demandeCrRep.findById(IdDemande).get());
    }




    @Override
    public DemandeCredit getDemandeCById(int Id) {
      DemandeCredit Dr = demandeCrRep.findById(Id).get();
      return Dr;


    }
    @Override
    public String MeilleurBanque(int idDemande){
        double difference=0;
        String BanqueGagnante="Desconnected";
        DemandeCredit dr = demandeCrRep.findById(idDemande).get();
        List<Bank> banks = BankRep.getBankByDemandeId(idDemande);
        for (Bank bank: banks){
            double diff=(bank.getCreditPotentielle()-dr.getMontantDemande());
            difference=999999999;
            if (diff < difference){
                difference=diff;
               BanqueGagnante="la banque est "+bank.getNom().toString();

            }else {
                BanqueGagnante="mafamach";
            }

      }
        return BanqueGagnante+=" avec une difference de  "+difference+" dinars";

    };

}
