package com.esprit.dari.services.CreditServices;

import com.esprit.dari.dao.CreditDAO.BankRepository;
import com.esprit.dari.entities.CreditEntities.Bank;
import com.esprit.dari.entities.CreditEntities.DemandeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankImpl implements IBankService {


    @Autowired
    BankRepository BankRep;
    @Autowired
    IDemandeCreditService demandeCrSer;
    @Override
    public void AjouterBank(Bank bank) {
       BankRep.save(bank);


    }

    @Override
    public void SupprimerBank(int id) {
BankRep.delete(BankRep.findById(id).get());

    }
    @Override
    public List<Bank>getBankAprrouver(){
       List<Bank> banks= BankRep.getBankApprouver("true");
    return  banks;
    };

    @Override
    public Bank ModifierBank(int id,Bank b) {

        Bank bo=BankRep.findById(id).get();
        bo.setAgeMax(b.getAgeMax());
        bo.setCreditPotentielle(b.getCreditPotentielle());
        bo.setMarge(b.getMarge());
        bo.setNom(b.getNom());
        bo.setOffre(b.getOffre());
        bo.setTeaux(b.getTeaux());

        BankRep.save(bo);
return bo;

    }


    @Override
    public Bank getBankById(int IdBank) {
Bank bk=BankRep.findById(IdBank).get();
return bk;
    }

    @Override
    public List<Bank> getBankByOffre(double offre) {
         List<Bank> banks=BankRep.getBankByOffre(offre);
    return banks;
    }

    @Override
    public List<Bank> getAllbanks() {
        List<Bank> banks= (List<Bank>) BankRep.findAll();
return banks;
    }
    @Override
    public List<Bank> getBanksByDemande(int idDemande){

        List<Bank> banks= BankRep.getBankByDemandeId(idDemande);
    return banks;};



    @Override
    public String calculerMarge(int idDemande) {

String test="false";
        DemandeCredit demande = demandeCrSer.getDemandeCById(idDemande);
        List<Bank> banks=(List<Bank>) BankRep.getBankByDemandeId(demande.getId());

        for( Bank bank : banks)
        {
            if ((demande.getAge() > 73)) {
                test="mat3adech jemla" ;
                bank.setMarge(100);
            } else {
                switch (bank.getNom()) {
                    case BH:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 3);
                                } else {
                                    bank.setMarge( 2.6);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.4);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3600) {
                                    bank.setMarge( 4.6);
                                } else {
                                    bank.setMarge( 3.2);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 7000) {
                                    bank.setMarge( 5.6);
                                } else {
                                    bank.setMarge( 5.2);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.4);
                                } else {
                                    bank.setMarge( 1.8);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 3.6);
                                } else {
                                    bank.setMarge( 3.1);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                        }
                        break;
                    case BT:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.8);
                                } else {
                                    bank.setMarge( 2.4);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.8);
                                } else {
                                    bank.setMarge( 2.4);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3600) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 7000) {
                                    bank.setMarge( 5.9);
                                } else {
                                    bank.setMarge( 5.4);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.4);
                                } else {
                                    bank.setMarge( 2.0);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.2);
                                } else {
                                    bank.setMarge( 1.8);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 3.4);
                                } else {
                                    bank.setMarge( 3.1);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                        }
                        break;
                    case ATB:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 3.8);
                                } else {
                                    bank.setMarge( 3.4);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 1.8);
                                } else {
                                    bank.setMarge( 1.4);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3600) {
                                    bank.setMarge( 3.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 7000) {
                                    bank.setMarge( 4.9);
                                } else {
                                    bank.setMarge( 4.4);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 3.4);
                                } else {
                                    bank.setMarge( 3.0);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.4);
                                } else {
                                    bank.setMarge( 1.8);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 4.6);
                                } else {
                                    bank.setMarge( 4.1);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                        }
                    case BNA:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 3.6);
                                } else {
                                    bank.setMarge( 3.3);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.8);
                                } else {
                                    bank.setMarge( 0.4);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3600) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.4);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 7000) {
                                    bank.setMarge( 5.7);
                                } else {
                                    bank.setMarge( 5.4);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 3.4);
                                } else {
                                    bank.setMarge( 3.0);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.3);
                                } else {
                                    bank.setMarge( 1.7);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 4.2);
                                } else {
                                    bank.setMarge( 3.9);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2000) {
                                    bank.setMarge( 2.4);
                                } else {
                                    bank.setMarge( 1.8);
                                }
                                break;
                        }
                        break;
                    case STB:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 5.4);
                                } else {
                                    bank.setMarge( 3.3);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 2500) {
                                    bank.setMarge( 1.4);
                                } else {
                                    bank.setMarge( 0.4);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3700) {
                                    bank.setMarge( 3.6);
                                } else {
                                    bank.setMarge( 2.4);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 9000) {
                                    bank.setMarge( 6.1);
                                } else {
                                    bank.setMarge( 5.4);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 4000) {
                                    bank.setMarge( 3.4);
                                } else {
                                    bank.setMarge( 3.0);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 2.3);
                                } else {
                                    bank.setMarge( 1.7);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3600) {
                                    bank.setMarge( 4.0);
                                } else {
                                    bank.setMarge( 3.4);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2400) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.2);
                                }
                                break;
                        }

                        break;
                    case BIAT:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 3.6);
                                } else {
                                    bank.setMarge( 3.3);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 2500) {
                                    bank.setMarge( 2.8);
                                } else {
                                    bank.setMarge( 0.4);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3700) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.4);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 9000) {
                                    bank.setMarge( 5.8);
                                } else {
                                    bank.setMarge( 5.4);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 4000) {
                                    bank.setMarge( 3.4);
                                } else {
                                    bank.setMarge( 3.0);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 3000) {
                                    bank.setMarge( 2.1);
                                } else {
                                    bank.setMarge( 1.7);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3600) {
                                    bank.setMarge( 4.2);
                                } else {
                                    bank.setMarge( 3.9);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2400) {
                                    bank.setMarge( 2.2);
                                } else {
                                    bank.setMarge( 1.8);
                                }
                                break;
                        }

                        break;
                    case ATTIJARI:
                        switch (demande.getProfession()) {
                            case CADRE:
                                if (demande.getSalaire() > 4200) {
                                    bank.setMarge( 3.65);
                                } else {
                                    bank.setMarge( 3.3);
                                }
                                break;
                            case ENSAIGNANT:
                                if (demande.getSalaire() > 3200) {
                                    bank.setMarge( 2.5);
                                } else {
                                    bank.setMarge( 1.4);
                                }
                                break;
                            case GESTIONNAIRE:
                                if (demande.getSalaire() > 3800) {
                                    bank.setMarge( 2.6);
                                } else {
                                    bank.setMarge( 2.4);
                                }
                                break;
                            case MEDECIN:
                                if (demande.getSalaire() > 8600) {
                                    bank.setMarge( 5.5);
                                } else {
                                    bank.setMarge( 5.0);
                                }
                                break;
                            case OUVRIER:
                                if (demande.getSalaire() > 4500) {
                                    bank.setMarge( 3.4);
                                } else {
                                    bank.setMarge( 3.0);
                                }
                                break;
                            case INFIRMIER:
                                if (demande.getSalaire() > 3800) {
                                    bank.setMarge( 2.3);
                                } else {
                                    bank.setMarge( 1.7);
                                }
                                break;
                            case INGENIEUR:
                                if (demande.getSalaire() > 3400) {
                                    bank.setMarge( 4.2);
                                } else {
                                    bank.setMarge( 3.9);
                                }
                                break;
                            case TECHNICIEN:
                                if (demande.getSalaire() > 2600) {
                                    bank.setMarge( 2.4);
                                } else {
                                    bank.setMarge( 2.0);
                                }
                                break;
                        }

                        break;


                }
                BankRep.CalculMarge(bank.getMarge(),bank.getId());
test="t3ada";
            }
        }

   return test; }
@Override
    public String calculerOffre(int idDemande){
String test="false";
        DemandeCredit demande = demandeCrSer.getDemandeCById(idDemande);
        List<Bank> banks=(List<Bank>) BankRep.getBankByDemandeId(idDemande);

        for( Bank bank : banks) {
            if (demande.getAge() > 73) {
                bank.setOffre(0);
                test="mat3adech";
            } else {
                bank.setOffre( (bank.getCreditPotentielle()  / demande.getDuree())/12);
                test="t3ada";

            }
           BankRep.CalculerOffre(bank.getOffre(),bank.getId());
        }
    return  test;}




@Override
    public String calculerCreditPotientielle(int idDemande) {
        String test="false";
        DemandeCredit demande = demandeCrSer.getDemandeCById(idDemande);
        List<Bank> banks=(List<Bank>) BankRep.getBankByDemandeId(idDemande);

        for (Bank bank : banks) {

            bank.setTeaux(bank.getMarge()+bank.getTmm());
            double creditPotent = 0;
            if (demande.getSalaire() > 1990) {

                creditPotent = (((demande.getSalaire() * 0.25) * 12 * demande.getDuree() )/100) *(100+ bank.getTeaux());

            } else {
                creditPotent = (((demande.getSalaire() * 0.20) * 12 * demande.getDuree())/100) *(100+bank.getTeaux());
            }
            if (creditPotent > demande.getMontantDemande()) {
                bank.setCreditPotentielle( creditPotent);
                test="t3ada";


            } else if(creditPotent>25000.0){
                bank.setCreditPotentielle( creditPotent);
                test="t3ada ama sghir";
            } else {
                bank.setCreditPotentielle(0);
                test="mat3adech";
            }
BankRep.CalculerCreditPoten(bank.getCreditPotentielle(),bank.getId());

        }
 return test;   }

    @Override
    public String ApprouverBank(int id) {
        Bank bank =BankRep.findById(id).get();
        BankRep.AprrouverBank("true",id);

        return "cette banque est approuvée";
    }


}
