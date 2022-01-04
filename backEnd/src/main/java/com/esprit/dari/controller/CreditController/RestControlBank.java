package com.esprit.dari.controller.CreditController;

import com.esprit.dari.entities.CreditEntities.Bank;
import com.esprit.dari.services.CreditServices.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank")
public class RestControlBank {
@Autowired
   IBankService bankService;
//ajouter une bank

    @PostMapping("/ajouterBank")
    @ResponseBody
    public Bank ajouterBank(@RequestBody Bank bank)
    {
         bankService.AjouterBank(bank);
        return bank;
    }
    //modifier une bank
    @PutMapping(value = "/modifierBank/{id}")
  @ResponseBody
    public Bank ModifierBank(@PathVariable("id" )int id,@RequestBody Bank bank) {
         return bankService.ModifierBank(id,bank);

    }
    //getBankById
    @GetMapping(value = "/getBankbyId/{id}")
    @ResponseBody
    public Bank getBankById(@PathVariable("id")int id) {
      return  bankService.getBankById(id);

    }
    @GetMapping(value = "/getBankApprouvee")
    @ResponseBody
    public List<Bank> getBankApprouver(){
        return bankService.getBankAprrouver();
    };
    @GetMapping(value = "/getBankbyDemande/{id}")
    @ResponseBody
    public List<Bank> getBankByDemande(@PathVariable("id") int idDemande){
        return bankService.getBanksByDemande(idDemande);
    }
    @GetMapping(value="/getBankOffre/{offre}")
    @ResponseBody
    public List<Bank> getBankByOffre(@PathVariable("offre") Double offre){
        return bankService.getBankByOffre(offre);

    }
    @GetMapping(value="/getBanks")
    @ResponseBody
    public List<Bank> getAllbanks(){
        return bankService.getAllbanks();
    }

    @DeleteMapping("/BankDelete/{Id}")
    @ResponseBody
    public void Supprimerbank (@PathVariable("Id") int id) {
        bankService.SupprimerBank(id);
    }

    @PutMapping(value="/calculerMarge/{id}")
    @ResponseBody
    public String calculerMarge(@PathVariable("id") int id) {
        return bankService.calculerMarge(id);
    };
    @PutMapping(value="/calculerOffre/{id}")
    @ResponseBody
    public String calculerOffre(@PathVariable("id") int idDemande){
        return bankService.calculerOffre(idDemande);
    };
    @PutMapping(value="/calculerCreditPotentielle/{id}")
    @ResponseBody
    public String calculerCreditPotientielle(@PathVariable("id") int idDemande) {
     return    bankService.calculerCreditPotientielle(idDemande);


    };

@PutMapping(value = "/approuverBank/{id}")
@ResponseBody
public String ApprouverBank(@PathVariable("id")int id){ return bankService.ApprouverBank(id);  };








}
