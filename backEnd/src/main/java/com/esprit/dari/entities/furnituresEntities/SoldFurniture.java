package com.esprit.dari.entities.furnituresEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SoldFurniture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long soldfurnitureId;

    private Float price;
    private String name;
    private Integer quantity;

    @ManyToOne
    private Command commands;

    public Long getSoldfurnitureId() {
        return soldfurnitureId;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoldfurnitureId(Long soldfurnitureId) {
        this.soldfurnitureId = soldfurnitureId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Command getCommands() {
        return commands;
    }

    public void setCommands(Command commands) {
        this.commands = commands;
    }
}
