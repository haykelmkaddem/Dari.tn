package com.esprit.dari.dao.userrepository;

import com.esprit.dari.entities.userentity.Trasability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TrasabilityRepository extends JpaRepository<Trasability,String> {
    List<Trasability> findAllByRequest(String request);
}
