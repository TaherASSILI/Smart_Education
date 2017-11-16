package com.example.user.smart_education;

/**
 * Created by user on 13/11/2017.
 */

public class Utilisateur {

    private int id;
    private String nom;
    private String role;
    private String password;
    private String email;
    private String numInscription;

    public Utilisateur(String role, String nom, String password, String email, String numInscription) {
        this.role = role;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.numInscription = numInscription;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNumInscription() {
        return numInscription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumInscription(String numInscription) {
        this.numInscription = numInscription;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
