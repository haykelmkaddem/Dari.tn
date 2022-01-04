package com.esprit.dari.entities.furnituresEntities;

import com.esprit.dari.entities.userentity.UserDari;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    private Integer quantity;

    @ManyToOne
    private Furniture furnitures;

    @ManyToOne
    private UserDari users;

    public Long getBasketId() {
        return basketId;
    }



    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Furniture getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(Furniture furnitures) {
        this.furnitures = furnitures;
    }

    public UserDari getUsers() {
        return users;
    }

    public void setUsers(UserDari users) {
        this.users = users;
    }

}
