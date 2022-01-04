package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.dao.furnituresRepository.CommandRepository;
import com.esprit.dari.dao.furnituresRepository.TransporterRepository;
import com.esprit.dari.entities.furnituresEntities.Command;
import com.esprit.dari.entities.furnituresEntities.Transportor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import java.util.List;

@Service
public class TransporterServiceImpl implements ITransporterService{

    @Autowired
    TransporterRepository transporterRepository;
    @Autowired
    CommandRepository commandRepository;

    @Value("${app.twillio.accountSID}")
    private String ACCOUNT_SID;

    @Value("${app.twillio.authToken}")
    private String AUTH_TOKEN;


    @Override
    public Long ajouterTransportor(Transportor transportor) {
        transporterRepository.save(transportor);
        return transportor.getTransporterId();
    }

    @Override
    public void deleteTransportorById(Long transportorId) {
        transporterRepository.deleteById(transportorId);
    }

    @Override
    public List<Transportor> getAllTransportor() {
        return (List<Transportor>) transporterRepository.findAll();
    }

    @Override
    public void affecterTransporterToCommand(Long transportorId, Long commandId) {
        Transportor transportor = transporterRepository.findById(transportorId).get();
        Command command = commandRepository.findById(commandId).get();
        if (command.getUsers().getVille() == transportor.getSecteur()){
            command.setTransporters(transportor);
            commandRepository.save(command);
        }else {
            System.out.println("il faut que le transporteur travail dans le meme ville du client !! ");
        }
    }


    @Override
    public void changeCommandEtatAndTransporter(Long commandId, Long transporterId) {
        Command command = commandRepository.findById(commandId).get();
        Transportor transportor = transporterRepository.findById(transporterId).get();
        String string1 = command.getUsers().getVille();
        String string2 = transportor.getSecteur();
        if (string1.equalsIgnoreCase(string2)){
            command.setTransporters(transportor);
            command.setEtat("Shipped");
            commandRepository.save(command);

            try {
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message.creator( new PhoneNumber("+21628176222"), new PhoneNumber("+15046084562"),"body") // to:to which no  you want to send sms
                        .setMediaUrl(Arrays.asList(URI.create("https://demo.twilio.com/owl.png")))     // from: twillio given phone no
                        .setStatusCallback(URI.create("http://postb.in/1234abcd"))                      // body : text message
                        .create();

                System.out.println(message);
                System.out.println(message.getSid());

            }catch(Exception e) {

                e.printStackTrace();

            }
        }else {
            System.out.println("il faut que le transporteur travail dans le meme ville du client !! ");
        }
    }

    @Override
    public Long modifiererTransportor(Long id, Transportor transportor) {
        Transportor transportor1 = transporterRepository.findById(id).get();
        transportor1.setFirstname(transportor.getFirstname());
        transportor1.setLastname(transportor.getLastname());
        transportor1.setDateofbirth(transportor.getDateofbirth());
        transportor1.setPhone_number(transportor.getPhone_number());
        transporterRepository.save(transportor1);
        return transportor1.getTransporterId();
    }

    @Override
    public Transportor getTransportorById(Long transportorId) {
        Transportor transportor = transporterRepository.findById(transportorId).get();
        return transportor;
    }
}
