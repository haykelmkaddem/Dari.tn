package com.esprit.dari.controller.CreditController;

import com.esprit.dari.entities.CreditEntities.DemandeCredit;
import com.esprit.dari.services.CreditServices.IDemandeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class IDemandeCreditImpl {
    @Autowired
    IDemandeCreditService demandeService;

    public void AjouterDemandeC(DemandeCredit demandeC){
        demandeService.AjouterDemandeC(demandeC);
    };
    public DemandeCredit ModifierDemandeC(int idDemande,DemandeCredit demande){
       DemandeCredit dc= demandeService.ModifierDemandeC(idDemande,demande);
   return dc ; };
    public List<DemandeCredit> getAllDemandes(){
         return demandeService.getAllDemandes();

    };

  public   void SupprimerDemandeC(int IdDemande){
        demandeService.SupprimerDemandeC(IdDemande);
    };
    public DemandeCredit getDemandeCById(int Id) {
         return  demandeService.getDemandeCById(Id);
    };
    public String MeilleurBanque(int idDemande) {
        return demandeService.MeilleurBanque(idDemande);
    };

}
