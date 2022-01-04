package com.esprit.dari.services.user.impl;

import com.esprit.dari.controller.dto.RegisterForm;
import com.esprit.dari.dao.userrepository.RoleRepository;
import com.esprit.dari.dao.userrepository.UserDariRepository;
import com.esprit.dari.entities.userentity.RoleDari;
import com.esprit.dari.entities.userentity.UserDari;
import com.esprit.dari.services.user.AccountService;
import com.esprit.dari.services.user.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserDariRepository userDariRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;


    @Override
    public UserDari saveUser(UserDari user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDariRepository.save(user);
    }

    @Override
    public RoleDari saveRole(RoleDari role)
    {
        return roleRepository.save(role);

    }

    @Override
    public UserDari loadUserByUsername(String userName)
    {
        return userDariRepository.findByUsername(userName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {


        UserDari userDari = userDariRepository.findByUsername(username);
        RoleDari role = roleRepository.findByName(roleName);
        //puisque la methode Transactionnel ilajout le role ala table
        userDari.getRoleDaris().add(role);


    }

    @Override
    public List<UserDari> getAllUser() {
        return (List<UserDari>) userDariRepository.findAll();
    }


    @Override
    public void deleteUserById(long userId) {
        userDariRepository.deleteById(userId);
    }

    @Override
    public void updateMotDePasse(String email, String password) {
        UserDari userDari = loadUserByEmail(email);
        userDari.setPassword(bCryptPasswordEncoder.encode(password));
        userDariRepository.save(userDari);
    }

    @Override
    public UserDari loadUserByEmail(String email) {
        return userDariRepository.findByEmail(email);
    }
    @Override
    public void resetPassword(RegisterForm userForm) throws MessagingException {
        UserDari usermail = loadUserByEmail(userForm.getEmail());
        if (usermail == null) throw new RuntimeException("this mail is not exists");
        emailService.sendRestPasswordMessage(usermail.getEmail());
    }

    @Override
    public void comfirm(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        UserDari usermail = loadUserByEmail(email);
        if (usermail == null) throw new RuntimeException("this mail is not exists");
        usermail.setActivated(true);
        userDariRepository.save(usermail);
    }

    @Override
    public UserDari register(RegisterForm userForm) throws MessagingException {
        if (!userForm.getPassword().equals(userForm.getRepassword()))
            throw new RuntimeException("you must confirm your password");

        UserDari username=loadUserByUsername(userForm.getUsername());
        if (username!=null) throw new RuntimeException("this User is already exists");
        UserDari usermail=loadUserByEmail(userForm.getEmail());
        if (usermail!=null) throw new RuntimeException("this mail is already exists");
        UserDari userDari = new UserDari();
        userDari.setEmail(userForm.getEmail());
        userDari.setUsername(userForm.getUsername());
        userDari.setPassword(userForm.getPassword());
        userDari.setPhoneNumber(userForm.getNumPhone());
        userDari.setActivated(false);
        saveUser(userDari);
        addRoleToUser(userForm.getUsername(),"USER");

        emailService.sendComfirmationMessage(userForm.getEmail());
        return userDari;
    }

    @Override
    public void updatefirstNameAndlastById(String firstName, String lastName, Long userId) {
        UserDari user = userDariRepository.findById(userId).get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userDariRepository.save(user);
    }
}
