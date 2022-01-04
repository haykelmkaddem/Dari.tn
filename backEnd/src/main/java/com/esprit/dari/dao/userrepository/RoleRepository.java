package com.esprit.dari.dao.userrepository;


import com.esprit.dari.entities.userentity.RoleDari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDari,Long> {
    public RoleDari findByName (String roleName);

}
