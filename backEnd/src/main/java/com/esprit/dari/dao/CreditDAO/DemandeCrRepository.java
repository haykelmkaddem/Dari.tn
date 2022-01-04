package com.esprit.dari.dao.CreditDAO;

import com.esprit.dari.entities.CreditEntities.DemandeCredit;
import com.esprit.dari.entities.CreditEntities.Profession;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DemandeCrRepository extends CrudRepository<DemandeCredit, Integer> {

@Modifying
    @Transactional
    @Query("update DemandeCredit cr set cr.salaire=:salaire,cr.montantDemande=:montantdemade,cr.duree=:duree,cr.age=:age,cr.profession=:profession where cr.id=:id")
public void ModifierDemande(@Param("salaire") double salaire, @Param("montantdemade") double montantdemade, @Param("duree") int duree,  @Param("age") int age,@Param("profession") Profession profession, @Param("id") int id);

}
