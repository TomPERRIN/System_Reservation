package com.uca.aeroport;

public class Ville {
    private String nom;
    
    public Ville (String nom){
        setNom(nom);
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom (String nom) throws IllegalArgumentException{
        if (nom == null){
            throw new IllegalArgumentException("Le nom de ville ne peut pas être vide");
        }
        this.nom = nom;
    }
}
