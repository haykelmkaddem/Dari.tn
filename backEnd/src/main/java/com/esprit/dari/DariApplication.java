package com.esprit.dari;


import com.esprit.dari.services.CreditServices.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import com.esprit.dari.dao.userrepository.TaskRepository;
import com.esprit.dari.entities.CallEtats;
import com.esprit.dari.entities.userentity.RoleDari;
import com.esprit.dari.entities.userentity.Task;
import com.esprit.dari.entities.userentity.UserDari;
import com.esprit.dari.security.CallConstants;
import com.esprit.dari.services.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;


import java.net.URI;
import java.util.stream.Stream;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
@EnableAspectJAutoProxy
public class DariApplication implements CommandLineRunner {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CallConstants callConstant;

    public static void main(String[] args) {
        SpringApplication.run(DariApplication.class, args);

    }

    @Bean
    public CallConstants callConstants(){return new CallConstants();}
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
      accountService.saveUser(new UserDari(null,"admin","admin","houssemeddine.marsaoui@esprit.tn",true,null));
      accountService.saveUser(new UserDari(null,"user","user",true,null));
        accountService.saveRole(new RoleDari(null,"ADMIN"));
        accountService.saveRole(new RoleDari(null,"USER"));
        accountService.saveRole(new RoleDari(null,"PASSWORD"));
        accountService.addRoleToUser("admin","ADMIN");
        accountService.addRoleToUser("admin","USER");
        accountService.addRoleToUser("User","USER");

    }

}