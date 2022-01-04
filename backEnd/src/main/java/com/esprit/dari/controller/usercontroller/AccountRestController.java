package com.esprit.dari.controller.usercontroller;


import com.esprit.dari.controller.dto.RegisterForm;
import com.esprit.dari.entities.userentity.UserDari;
import com.esprit.dari.services.user.AccountService;
import com.esprit.dari.services.user.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;


@RestController
@RequestMapping("user")
public class AccountRestController {


    @Autowired
    private AccountService accountService;




    @PostMapping("/register")
    public UserDari register(@RequestBody RegisterForm userForm) throws MessagingException {

        return accountService.register(userForm);
    }

    // URL : http://localhost:8081/dari/getAllUsers
    @GetMapping(value = "/getAllUsers")
    public List<UserDari> getAllUser() {
        return accountService.getAllUser();
    }
    // URL : http://localhost:8081/dari/getAllUsers

//    @GetMapping(value = "/send")
//    public void send() throws MessagingException {
//        emailService.sendSimpleMessage("imededdine.marsaoui@esprit.tn","test","email work");
//    }

    // URL : http://localhost:8081/dari/deleteUserById/1
    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") int UserId) {
        accountService.deleteUserById(UserId);
    }

  //  Modifier mdp : http://localhost:8081/dari/updatepassword/1/password

    @PutMapping(value = "/updatepassword/{password}")
    public void updateMotDePasse(@PathVariable("password") String password, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        accountService.updateMotDePasse(email,password);


    }

    @PatchMapping("/forgetpassword")
    public void forgetpassword(@RequestBody RegisterForm userForm) throws MessagingException {
        accountService.resetPassword(userForm);
    }

    @PatchMapping("/confirmaccount")
    public void confirmAccount(Authentication authentication) {
        accountService.comfirm(authentication);
    }


}
