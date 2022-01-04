package com.esprit.dari.entities.CreditEntities;

import com.esprit.dari.entities.userentity.UserDari;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class DemandeCredit implements Serializable {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  int id;
private double salaire;
private  double montantDemande;
private int duree;
private int age;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserDari userDari;
    @Enumerated(EnumType.STRING)
    //@NotNull
    private Profession profession;
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "demandeId")
    private Collection<Bank> banks = new ArrayList<>();

    public DemandeCredit(int id, double salaire, double montantDemande, int duree, int age, Profession profession) {
        this.id = id;
        this.salaire = salaire;
        this.montantDemande = montantDemande;
        this.duree = duree;
        this.age = age;
        this.profession = profession;
    }

    public DemandeCredit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public double getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(double montantDemandé) {
        this.montantDemande = montantDemandé;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int durée) {
        this.duree = durée;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public UserDari getUserDari() {
        return userDari;
    }

    public void setUserDari(UserDari userDari) {
        this.userDari = userDari;
    }
}
