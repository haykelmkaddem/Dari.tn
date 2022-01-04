package com.esprit.dari.controller.CreditController;

import com.esprit.dari.entities.CreditEntities.Bank;
import com.esprit.dari.entities.CreditEntities.Banque;
import com.esprit.dari.entities.CreditEntities.DemandeCredit;
import com.esprit.dari.services.CreditServices.IBankService;
import com.esprit.dari.services.CreditServices.IDemandeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestControlDemandeCredit {
    @Autowired
    IDemandeCreditService demandeService;
    @Autowired
    IBankService iBankService;

@PostMapping(value = "/ajouterDemande")
    @ResponseBody
    public DemandeCredit ajouterDemande(@RequestBody DemandeCredit demandeC ){
   demandeService.AjouterDemandeC(demandeC);

    Bank b1= new Bank(Banque.valueOf("BIAT"),demandeC);
    Bank b2= new Bank(Banque.valueOf("ATB"),demandeC);
    Bank b3= new Bank(Banque.valueOf("ATTIJARI"),demandeC);
    Bank b4= new Bank(Banque.valueOf("BNA"),demandeC);
    Bank b5= new Bank(Banque.valueOf("BT"),demandeC);
    Bank b6= new Bank(Banque.valueOf("BH"),demandeC);
    Bank b7= new Bank(Banque.valueOf("STB"),demandeC);
    iBankService.AjouterBank(b1);
    iBankService.AjouterBank(b2);
    iBankService.AjouterBank(b3);
    iBankService.AjouterBank(b4);
    iBankService.AjouterBank(b5);
    iBankService.AjouterBank(b6);
    iBankService.AjouterBank(b7);
    return  demandeC;
};

 @PutMapping(value = "/modifierDemande/{id}")
 @ResponseBody
    public DemandeCredit modifierDemande(@PathVariable("id") int id,@RequestBody DemandeCredit demande){
     DemandeCredit dc = demandeService.ModifierDemandeC(id,demande);
    return dc;
 };
 @GetMapping(value="/getAlldemands")
  @ResponseBody
    public List<DemandeCredit> getAllDemandes(){
       return demandeService.getAllDemandes();
    };
@DeleteMapping(value="/DeleteDemande/{id}")
@ResponseBody
    void SupprimerDemandeC(@PathVariable("id") int IdDemande){
        demandeService.SupprimerDemandeC(IdDemande);
    };
@GetMapping(value="/getDemandebyId/{id}")
@ResponseBody
    public DemandeCredit getDemandeCById(@PathVariable("id") int Id) {
        return demandeService.getDemandeCById(Id);
    };
@GetMapping(value="/MeuilleurBanque/{id}")
    @ResponseBody
    String MeuilleurBanque(@PathVariable("id") int IdDemande){
  return  demandeService.MeilleurBanque(IdDemande);};

}

