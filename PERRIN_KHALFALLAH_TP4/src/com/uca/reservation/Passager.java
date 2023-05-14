package com.uca.reservation;

import java.util.ArrayList;
import java.util.Collection;

public class Passager {
    private String nom;
    private Collection<Reservation> reservations = new ArrayList<>();

    public Passager(String nom) throws IllegalArgumentException{
        //On utilise l'appel de cette méthode pour pas implémenter 2 fois le même test
        this.setNom(nom);   
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom) throws IllegalArgumentException{
        if (nom == null){
            throw new IllegalArgumentException("Le nom du passager ne peut pas être vide");
        }
        this.nom = nom;
    }

    public Collection<Reservation> getReservations(){
        return this.reservations;
    }

    public Reservation getReservation(String num) throws IllegalArgumentException{
        for (Reservation r : reservations){
            if (r.getNumero() == num){
                return r;
            }
        }
        throw new IllegalArgumentException("Reservation inexistante");
    }

    public void addReservation(Reservation r){
        if (!this.reservations.contains(r)){
            this.reservations.add(r);
        }
    }
    
    public void removeReservation(Reservation r){
        if (this.reservations.contains(r)){
            this.reservations.remove(r);
        }
    }
    /*
    public String getInfosPassager(){
        String infos = "Nom : " + nom + "Liste de reservations : \n";
        for (Reservation r : reservations){
            infos += "Numero de reservation : " + r.getNumero() + "\n";
        }
        return infos;
    }
    */
}
