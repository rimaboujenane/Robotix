package ift2255.robotix.Modeles;

import java.io.FileWriter;
import java.io.IOException;

public abstract class User {
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;

    public User(String nom, String prenom, String password, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
}
