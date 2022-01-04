package com.esprit.dari.services.CreditServices;

import com.esprit.dari.entities.CreditEntities.Bank;

import java.util.List;


public interface IBankService {
    public void AjouterBank(Bank bank);
    public void SupprimerBank(int IdBank);
    public Bank ModifierBank(int id,Bank b);
    public  Bank getBankById( int IdBank);
    public List<Bank> getBankAprrouver();
    public List<Bank> getBankByOffre( double offre);
    public List<Bank> getAllbanks();
    public List<Bank> getBanksByDemande(int idDemande);

    public String calculerMarge(int idDemande) ;

    public String calculerOffre(int idDemande);
    public String calculerCreditPotientielle(int idDemande) ;
    public String ApprouverBank(int id);

}
