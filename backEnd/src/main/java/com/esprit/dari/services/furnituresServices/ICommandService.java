package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.entities.furnituresEntities.Command;
import com.esprit.dari.entities.furnituresEntities.Furniture;
import com.itextpdf.text.DocumentException;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;

public interface ICommandService {

    public void ajouterCommand(Long user, String code, Boolean transporterValidation) throws FileNotFoundException, DocumentException, MessagingException;
    public void deleteCommandById(Long commandId);
    public List<Command> getAllCommand();
    public Long modifiererCommand(Long id, Command command);
    public Command getCommandById(Long commandId);

}
