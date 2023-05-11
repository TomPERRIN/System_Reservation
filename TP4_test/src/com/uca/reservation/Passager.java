package com.uca.reservation;

import java.util.ArrayList;
import java.util.Collection;

public class Passager {
    private String nom;
    private Collection<Reservation> reservations = new ArrayList<>();

    public Passager(String nom){
        this.setNom(nom);   
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public Collection<Reservation> getReservations(){
        return this.reservations;
    }

    public Reservation getReservation(String num){
        for (Reservation r : reservations){
            if (r.getNumero() == num){
                return r;
            }
        }
        System.out.println("Cette reservation n'existe pas ou le vol est fermé");
        return null;
    }

    public void addReservation(Reservation r){
        this.reservations.add(r);
    }
    
    public void removeReservation(Reservation r){
        this.reservations.remove(r);
    }

    public String getInfosPassager(){
        String infos = "Nom : " + nom + "Liste de reservations : \n";
        for (Reservation r : reservations){
            infos += "Numero de reservation : " + r.getNumero() + "\n";
        }
        return infos;
    }
}
