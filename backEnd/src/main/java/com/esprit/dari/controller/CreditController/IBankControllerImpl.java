package com.esprit.dari.controller.CreditController;

import com.esprit.dari.entities.CreditEntities.Bank;
import com.esprit.dari.services.CreditServices.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class IBankControllerImpl {
    @Autowired
    IBankService BankService;


    public void AjouterBank(Bank bank){
        BankService.AjouterBank( bank);

    };
    public void SupprimerBank(int idBank){
        BankService.SupprimerBank( idBank);

    };
    public Bank ModifierBank(int id,Bank b){
         return BankService.ModifierBank(id,b);


    };
    public  Bank getBankById( int IdBank){
        return BankService.getBankById(IdBank);
    };
    public List<Bank> getBankByOffre( double offre){
        return BankService.getBankByOffre(offre);
    };
    public List<Bank> getAllbanks(){
        return BankService.getAllbanks();
    };
    public List<Bank> getBankByDemande(int idDemande){return BankService.getBanksByDemande(idDemande);};
public List<Bank> getBankapprouvee(){return  BankService.getBankAprrouver();};
    public String calculerMarge(int idDemande) {
        return BankService.calculerMarge(idDemande);
    };

    public String calculerOffre(int idDemande){
        return BankService.calculerOffre(idDemande);
    };
    public String calculerCreditPotientielle(int idDemande) {
        return BankService.calculerCreditPotientielle(idDemande);
    };
    public String AprrouverBank(int idBank){return  BankService.ApprouverBank(idBank);};

}
