package com.esprit.dari.entities.userentity;



import com.esprit.dari.entities.furnituresEntities.Basket;
import com.esprit.dari.entities.furnituresEntities.Command;
import com.esprit.dari.entities.furnituresEntities.GiftPoints;

import com.esprit.dari.entities.userentity.RoleDari;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;


@Entity
public class UserDari implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String firstName;
    private String lastName;
    private String PhoneNumber;
    private String email;
    private boolean activated;
    private String imageUrl;

    private String ville;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleDari> roleDaris =new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy="users", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<GiftPoints> giftPoints = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="users", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Basket> baskets = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="users", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Command> commands = new ArrayList<>();


    public UserDari() {
    }


    public UserDari(Long userId, String username, String password, String firstName, String lastName, String email, boolean activated, String imageUrl, Collection<RoleDari> roleDaris) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.imageUrl = imageUrl;
        this.roleDaris = roleDaris;
    }

    public UserDari(Long userId, String username, String password,boolean activated ,Collection<RoleDari> roleDaris) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.activated = activated;
        this.roleDaris = roleDaris;
    }

    public UserDari(Long userId, String username, String password, String email,boolean activated, Collection<RoleDari> roleDaris) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activated =activated;
        this.roleDaris = roleDaris;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<GiftPoints> getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(List<GiftPoints> giftPoints) {
        this.giftPoints = giftPoints;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Collection<RoleDari> getRoleDaris() {
        return roleDaris;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public void setRoleDaris(Collection<RoleDari> roleDaris) {
        this.roleDaris = roleDaris;
    }

}
