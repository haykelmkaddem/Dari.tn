package com.esprit.dari.entities.CreditEntities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Bank implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double teaux;
    private double marge;
    private int ageMax;
    @Enumerated(EnumType.STRING)
    //@NotNull
    private Banque nomBank;
    private double creditPotentielle;
    private double offre;
    private static double tmm = 5.23;
    private  String approuvation ="false";
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private  DemandeCredit demandeId;

    public Bank() {
    }

    public Bank(int id, double teaux, double marge, int ageMax, Banque nomBank, double creditPotentielle, double offre, String approuvation) {
        this.id = id;
        this.teaux = teaux;
        this.marge = marge;
        this.ageMax = ageMax;
        this.nomBank = nomBank;
        this.creditPotentielle = creditPotentielle;
        this.offre = offre;
        this.approuvation=approuvation;
    }

    public Bank(Banque nomBank, DemandeCredit demandeId) {
        this.nomBank = nomBank;
        this.demandeId = demandeId;
    }


    public DemandeCredit getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(DemandeCredit demandeId) {
        this.demandeId = demandeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTeaux() {
        return teaux;
    }

    public void setTeaux(double teaux) {
        this.teaux = teaux;
    }

    public double getMarge() {
        return marge;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public Banque getNom() {
        return nomBank;
    }

    public void setNom(Banque nom) {
        nomBank = nomBank;
    }

    public double getCreditPotentielle() {
        return creditPotentielle;
    }

    public void setCreditPotentielle(double creditPotentielle) {
        this.creditPotentielle = creditPotentielle;
    }

    public double getOffre() {
        return offre;
    }

    public void setOffre(double offre) {
        this.offre = offre;
    }

    public String getApprouvation() {
        return approuvation;
    }

    public void setApprouvation(String approuvation) {
        this.approuvation = approuvation;
    }

    public static double getTmm() {
        return tmm;
    }
}
