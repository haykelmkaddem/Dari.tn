package com.esprit.dari.services.user;

import com.esprit.dari.controller.dto.RegisterForm;
import com.esprit.dari.entities.userentity.RoleDari;
import com.esprit.dari.entities.userentity.UserDari;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.util.List;


public interface AccountService  {
     UserDari saveUser(UserDari user);
     RoleDari saveRole(RoleDari role);
     UserDari loadUserByUsername(String username);
     void addRoleToUser(String username,String rolename);
     List<UserDari> getAllUser();
     void deleteUserById(long userId);
     void updateMotDePasse(String email, String password);
     UserDari loadUserByEmail(String email);
    void updatefirstNameAndlastById(String firstName, String lastName, Long userId);
    void resetPassword(RegisterForm userForm) throws MessagingException;

    void  comfirm(Authentication authentication);

    UserDari register(RegisterForm userForm) throws MessagingException;
}
