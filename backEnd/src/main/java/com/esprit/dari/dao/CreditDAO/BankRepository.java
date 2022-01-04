package com.esprit.dari.dao.CreditDAO;

import com.esprit.dari.entities.CreditEntities.Bank;
import com.esprit.dari.entities.CreditEntities.Banque;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankRepository  extends CrudRepository<Bank, Integer> {
    @Query (value="Select * from bank b where b.offre=:offree",nativeQuery = true)
    public List<Bank> getBankByOffre(@Param("offree") double offre);
    @Query(value = "select offre from bank b where b.id=:idbank",nativeQuery = true)
    public Bank GetoffreBank(@Param("id") int idbank);
    @Query(value = "select * from bank b  where b.approuvation=:approuver" ,nativeQuery = true)
    public List<Bank> getBankApprouver(@Param("approuver") String approuver);
    @Query(value = "select b from Bank b join b.demandeId dm where dm.id=:demandeid",nativeQuery = false)
    public List<Bank> getBankByDemandeId(@Param("demandeid") int demandeid);


    @Modifying
    @Transactional
    @Query("UPDATE Bank b SET b.teaux=:teaux,b.marge=:marge,b.ageMax=:agemax,b.nomBank=:nombank,b.creditPotentielle=:creditpotentielle,b.offre=:offre,b.approuvation=:approuvation where b.id=:id")
    public void modifierBank( @Param("teaux")double teaux, @Param("marge")double marge, @Param("agemax")int agemax, @Param("nombank")Banque nombank, @Param("creditpotentielle")double creditpotentielle, @Param("offre")double offre, @Param("approuvation") String approuvation , @Param("id") int id);
@Modifying
    @Transactional
    @Query("update Bank b set b.marge=:marge where b.id=:id")
    public int CalculMarge(@Param("marge") double marge,@Param("id") int id);
@Modifying
    @Transactional
    @Query("update Bank b set b.offre=:offre where b.id=:id")
    public void CalculerOffre(@Param("offre") double offre,@Param("id") int id);
@Modifying
    @Transactional
    @Query("update Bank b set b.creditPotentielle=:creditpotentielle where b.id=:id")
    public void CalculerCreditPoten(@Param("creditpotentielle") double creditpotentielle,@Param("id") int id);
@Modifying
    @Transactional
    @Query("update  Bank b set b.approuvation=:approuvation where b.id=:id")
    public void AprrouverBank(@Param("approuvation")String approuvation,@Param("id") int id);






}

