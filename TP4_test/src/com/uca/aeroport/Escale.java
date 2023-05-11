package com.uca.aeroport;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Escale {
    private Date arrivee;
    private Date depart;
    private Aeroport aeroport;


    public Escale(){
    }

    public void setArrivee(Date d){
        this.arrivee = d;
    }

    public Date getArrivee(){
        return this.arrivee;
    }

    public void setDepart(Date d){
        this.arrivee = d;
    }

    public Date getDate(){
        return this.depart;
    }

    public void setAeroport(Aeroport aeroport){
        this.aeroport = aeroport;
    }

    public Aeroport getAeroport(){
        return this.aeroport;
    }

    public Duration obtenirDuree() {
        if(this.depart != null && this.arrivee != null) {
            return Duration.of(depart.getTime() - arrivee.getTime(), ChronoUnit.MILLIS);
        }
        return null;
    }

    public String getInfosEscale(){
        return "Arrivee prévue : " + this.arrivee + " à " + this.aeroport.getNom() 
        + " puis départ prevus : " + this.depart +"\n";

    }
}
