package com.esprit.dari.entities.furnituresEntities;

import com.esprit.dari.entities.userentity.UserDari;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GiftPoints implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giftpointsId;

    private Integer points;
    private String etat;
    @Column(unique = true)
    private String code;
    private LocalDate begin_date;
    private LocalDate end_date;
    private Float value;

    @ManyToOne
    private UserDari users;


    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Long getGiftpointsId() {
        return giftpointsId;
    }

    public void setGiftpointsId(Long giftpointsId) {
        this.giftpointsId = giftpointsId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public UserDari getUsers() {
        return users;
    }

    public void setUsers(UserDari users) {
        this.users = users;
    }
}
