package com.esprit.dari.entities.furnituresEntities;

import com.esprit.dari.entities.userentity.UserDari;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Command implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commandId;

    private String etat;
    private Date date;
    private Float total_price;

    private Boolean transporterValidation;
    private String code;

    @JsonIgnore
    @ManyToOne
    private Transportor transporters;

    @OneToMany(mappedBy="commands", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SoldFurniture> soldFurnitures = new ArrayList<>();

    @ManyToOne
    private UserDari users;


    public Boolean getTransporterValidation() {
        return transporterValidation;
    }

    public void setTransporterValidation(Boolean transporterValidation) {
        this.transporterValidation = transporterValidation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public Transportor getTransporters() {
        return transporters;
    }

    public void setTransporters(Transportor transporters) {
        this.transporters = transporters;
    }

    public List<SoldFurniture> getSoldFurnitures() {
        return soldFurnitures;
    }

    public void setSoldFurnitures(List<SoldFurniture> soldFurnitures) {
        this.soldFurnitures = soldFurnitures;
    }

    public UserDari getUsers() {
        return users;
    }

    public void setUsers(UserDari users) {
        this.users = users;
    }
}
