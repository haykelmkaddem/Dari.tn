package com.esprit.dari.services.furnituresServices;


import com.esprit.dari.dao.furnituresRepository.*;
import com.esprit.dari.dao.userrepository.UserDariRepository;
import com.esprit.dari.entities.furnituresEntities.*;
import com.esprit.dari.entities.userentity.UserDari;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.istack.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class CommandServiceImpl implements ICommandService{

    @Autowired
    CommandRepository commandRepository;
    @Autowired
    UserDariRepository userDariRepository;
    @Autowired
    FurnitureRepository furnitureRepository;
    @Autowired
    SoldFurnitureRepository soldFurnitureRepository;
    @Autowired
    GiftPointsRepository giftPointsRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    LivraisonRepository livraisonRepository;
    @Autowired
    TransporterRepository transporterRepository;
    @Value("${app.twillio.accountSID}")
    private String ACCOUNT_SID;

    @Value("${app.twillio.authToken}")
    private String AUTH_TOKEN;




    @Override
    public void ajouterCommand(Long user, String code, Boolean transporterValidation) throws FileNotFoundException, DocumentException, MessagingException {
        UserDari userDari = userDariRepository.findById(user).get();
        Command command = new Command();
        Date aujourdhui = new Date();
        command.setDate(aujourdhui);
        command.setEtat("In preparation");

        Float total= 0.0f;
        Float weighttotal = 0.0f;
        List <Basket> basketListUser = basketRepository.getAllBasketByUserDari(user);
        // List<Basket> baskets = new ArrayList<>();
        for (Basket basket : basketListUser){
            Float fourPrice = basket.getFurnitures().getPrice()*basket.getQuantity();
            Float kilos = basket.getFurnitures().getWeight();
            weighttotal = weighttotal + kilos;
            total=total+fourPrice;
        }


        if ((transporterValidation == false) && (code != "null")){
            if (codev(code,user) == true){
                GiftPoints giftPoints = giftPointsRepository.getAllGiftPointsByCode(code);
                Float value = giftPoints.getValue();
                total= total - value;
                command.setCode(code);
                // tet3ada

                command.setTotal_price(total);
                command.setUsers(userDari);
                command.setTransporterValidation(transporterValidation);
                commandRepository.save(command);

                for (Basket basket : basketListUser){
                    SoldFurniture soldFurniture = new SoldFurniture();
                    soldFurniture.setPrice(basket.getFurnitures().getPrice());
                    soldFurniture.setName(basket.getFurnitures().getName());
                    soldFurniture.setQuantity(basket.getQuantity());
                    soldFurniture.setCommands(command);
                    soldFurnitureRepository.save(soldFurniture);
                    Furniture furniture = furnitureRepository.getFurnitureById(basket.getFurnitures().getFurnitureId());
                    int quantity = basket.getQuantity();
                    int stock = furniture.getStock();
                    furniture.setStock(stock-quantity);
                    furnitureRepository.save(furniture);
                    basketRepository.deleteById(basket.getBasketId());
                }
                String etat = "Ready";
                GiftPoints giftPoints2 = giftPointsRepository.getAllGiftPointsByUserAndEtat(etat,user);
                int points0 = giftPoints2.getPoints();
                GiftPoints giftPoints55 = new GiftPoints();
                giftPoints55.setEtat("Ready");
                int value2 = Math.round(total);
                int points = value2 / 2 ;
                giftPoints55.setPoints(points + points0);
                giftPoints55.setUsers(userDari);
                giftPointsRepository.save(giftPoints55);
                giftPointsRepository.delete(giftPoints2);

                email(user, command.getCommandId());

            } else {
                // ma tet3adech
                System.out.println("Vérifier Votre Code");
            }

        } else if ((transporterValidation == true) && (code == "null")){
            Livraison livraison = livraisonRepository.findLivraison();
            Float livraisonPrice = livraison.getKiloPrice();
            Float allkilosPrice = livraisonPrice * weighttotal;
            total= total+ allkilosPrice;
            // tet3ada
            command.setTotal_price(total);
            command.setUsers(userDari);
            command.setTransporterValidation(transporterValidation);
            commandRepository.save(command);

            for (Basket basket : basketListUser){
                SoldFurniture soldFurniture = new SoldFurniture();
                soldFurniture.setPrice(basket.getFurnitures().getPrice());
                soldFurniture.setName(basket.getFurnitures().getName());
                soldFurniture.setQuantity(basket.getQuantity());
                soldFurniture.setCommands(command);
                soldFurnitureRepository.save(soldFurniture);
                Furniture furniture = furnitureRepository.getFurnitureById(basket.getFurnitures().getFurnitureId());
                int quantity = basket.getQuantity();
                int stock = furniture.getStock();
                furniture.setStock(stock-quantity);
                furnitureRepository.save(furniture);
                basketRepository.deleteById(basket.getBasketId());
            }
            String etat = "Ready";
            GiftPoints giftPoints2 = giftPointsRepository.getAllGiftPointsByUserAndEtat(etat,user);
            int points0 = giftPoints2.getPoints();
            GiftPoints giftPoints55 = new GiftPoints();
            giftPoints55.setEtat("Ready");
            int value = Math.round(total);
            int points = value / 2 ;
            giftPoints55.setPoints(points + points0);
            giftPoints55.setUsers(userDari);
            giftPointsRepository.save(giftPoints55);
            giftPointsRepository.delete(giftPoints2);

            email(user, command.getCommandId());


        } else if ((transporterValidation == true) && (code != "null")){
            if (codev(code,user) == true){
                GiftPoints giftPoints = giftPointsRepository.getAllGiftPointsByCode(code);
                Float value = giftPoints.getValue();
                Livraison livraison = livraisonRepository.findLivraison();
                Float livraisonPrice = livraison.getKiloPrice();
                Float allkilosPrice = livraisonPrice * weighttotal;
                total= total - value + allkilosPrice;
                command.setCode(code);
                // tet3ada
                command.setTotal_price(total);
                command.setUsers(userDari);
                command.setTransporterValidation(transporterValidation);
                commandRepository.save(command);

                for (Basket basket : basketListUser){
                    SoldFurniture soldFurniture = new SoldFurniture();
                    soldFurniture.setPrice(basket.getFurnitures().getPrice());
                    soldFurniture.setName(basket.getFurnitures().getName());
                    soldFurniture.setQuantity(basket.getQuantity());
                    soldFurniture.setCommands(command);
                    soldFurnitureRepository.save(soldFurniture);
                    Furniture furniture = furnitureRepository.getFurnitureById(basket.getFurnitures().getFurnitureId());
                    int quantity = basket.getQuantity();
                    int stock = furniture.getStock();
                    furniture.setStock(stock-quantity);
                    furnitureRepository.save(furniture);
                    basketRepository.deleteById(basket.getBasketId());
                }
                String etat = "Ready";
                GiftPoints giftPoints2 = giftPointsRepository.getAllGiftPointsByUserAndEtat(etat,user);
                int points0 = giftPoints2.getPoints();
                GiftPoints giftPoints55 = new GiftPoints();
                giftPoints55.setEtat("Ready");
                int value1 = Math.round(total);
                int points = value1 / 2 ;
                giftPoints55.setPoints(points + points0);
                giftPoints55.setUsers(userDari);
                giftPointsRepository.save(giftPoints55);
                giftPointsRepository.delete(giftPoints2);

                email(user, command.getCommandId());

            } else {
                // ma tet3adech
                System.out.println("Vérifier Votre Code");
            }
        }



    }


    public void email(Long userId , Long commandId) {
        UserDari userDari = userDariRepository.findById(userId).get();
        Command command = commandRepository.findById(commandId).get();
        String smtpHost = "smtp.gmail.com"; //replace this with a valid host
        int smtpPort = 587; //replace this with a valid port

        String sender = "tttt57748@gmail.com"; //replace this with a valid sender email address
        String recipient = userDari.getEmail(); //replace this with a valid recipient email address
        String content = "BONJOUR "+userDari.getUsername()+"\n" +
                "MERCI D'AVOIR EFFECTUÉ VOS ACHATS SUR DARI.TN! \n" +
                "Ce Fichier Contient Les Détails De Votre Commande"; //this will be the text of the email
        String subject = "[Dari.tn] Confirmation De Commande"; //this will be the subject of the email

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "daritn.contact@gmail.com", "usdrjqkgplrccgkb");// Specify the Username and the PassWord
            }
        });

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream ,userId , commandId);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("#"+ command.getCommandId());

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            //send off the email
            Transport.send(mimeMessage);

            System.out.println("sent from " + sender +
                    ", to " + recipient +
                    "; server = " + smtpHost + ", port = " + smtpPort);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            //clean off
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }

    public void writePdf(OutputStream outputStream , Long userId , Long commandId) throws Exception {
        UserDari userDari = userDariRepository.findById(userId).get();
        Command command = commandRepository.findById(commandId).get();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("Réf. de commande              Date de commande\n"+
                command.getCommandId() + "                                          "+ command.getDate() +"\n \n "+
                "Nom et Prénom                                Ville                                 Email \n"+
                userDari.getUsername() +"                                "+ userDari.getVille() +"              "+ userDari.getEmail() +"\n \n \n"+
                "Produit                  Prix Unitaire                Qté               Prix total \n"
                ));
        List<SoldFurniture> soldFurnitureList = soldFurnitureRepository.getAllSoldFurnitureByCommands(commandId);
        for (SoldFurniture soldFurniture : soldFurnitureList){
            paragraph.add(new Chunk(
                    " "+soldFurniture.getName() +"                        "+ soldFurniture.getPrice() +"                            "+ soldFurniture.getQuantity() +"                   "+ soldFurniture.getPrice()*soldFurniture.getQuantity()+"\n"
            ));
        }
        paragraph.add(new Chunk("                                                                                                                                      Prix Totale\n"
        +"                                                                                                                                                                     "+
                "                                                                                                                               "+command.getTotal_price()+"Dt"
        ));

        document.add(paragraph);
        document.close();
    }




    public Boolean validationCode(String code, Long user){
        Boolean test = false;
        List<GiftPoints> giftPoints = giftPointsRepository.getAllGiftPointsByUserDari(user);
        for (GiftPoints gp : giftPoints){
            if ((code == gp.getCode()) && (gp.getEtat()=="ToUse")){
                test = true;
                GiftPoints giftPointsToChange = giftPointsRepository.getAllGiftPointsByCode(code);
                giftPointsToChange.setEtat("Used");
            }
        }
        return test;
    }

    public Boolean codev(String code , Long user){
        Boolean tt = false;
        GiftPoints giftPoints = giftPointsRepository.getAllGiftPointsByUserAndCode(code,user);
        if (giftPoints != null){
            GiftPoints giftPointsToChange = giftPointsRepository.getAllGiftPointsByCode(code);
            giftPointsToChange.setEtat("Used");
            tt = true;
        }
        return tt;
    }

    @Override
    public void deleteCommandById(Long commandId) {
        commandRepository.deleteById(commandId);

    }

    @Override
    public List<Command> getAllCommand() {

        return (List<Command>) commandRepository.findAll();
    }

    @Override
    public Long modifiererCommand(Long id, Command command) {
        Command command1 = commandRepository.findById(id).get();
        return null;
    }

    @Override
    public Command getCommandById(Long commandId) {
        Command command = commandRepository.findById(commandId).get();
        return command;
    }


}
