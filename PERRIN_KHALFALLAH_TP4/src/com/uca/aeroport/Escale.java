package com.uca.aeroport;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Escale {
    private Date arrivee;
    private Date depart;
    private Aeroport aeroport;
    private Vol vol;
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    //Pas de méthode de modification du vol et de l'aeroport car en cas d'erreur, création d'une nouvelle

    public Escale(Vol vol, Aeroport aeroport) throws IllegalArgumentException{
        if (vol == null || aeroport == null){
            throw new IllegalArgumentException("Une escale doit avoir un aeroport et un vol");
        }
        
        this.vol = vol;
        this.aeroport = aeroport;
        vol.addEscale(this);
    }

    public void setArrivee(Date d){
        //Si pas de départ alors on met l'arrivée telle quel. Sinon on vérifie qu'elle est bien avant le départ 
        //et entre les horaires de départ et d'arrivé du vol
        if (this.depart!=null && d.compareTo(this.depart)<0 
            && d.compareTo(vol.getDateArrivee())<0 && d.compareTo(vol.getDateDepart())>0){
            this.arrivee = d;
        }
        else if (this.depart == null){
            this.arrivee = d;
        }
        else {
            throw new IllegalArgumentException("La date de depart doit être avant celle d'arrivée");
        }
    }

    public void setArrivee(String d){
        Date date = null;
        try {
            date = format.parse(d);
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }
        if (this.depart!=null && date.compareTo(this.depart)<0
        && date.compareTo(vol.getDateArrivee())<0 && date.compareTo(vol.getDateDepart())>0){
            this.arrivee = date;
        }
        else if (this.depart == null){
            this.arrivee = date;
        }
        else {
            throw new IllegalArgumentException("La date de depart doit être avant celle d'arrivée");
        }
    }

    public Date getArrivee(){
        return this.arrivee;
    }

    public void setDepart(Date d){
        if (this.arrivee!=null && this.arrivee.compareTo(d)<0
        && d.compareTo(vol.getDateArrivee())<0 && d.compareTo(vol.getDateDepart())>0){
            this.arrivee = d;
        }
        else if (this.arrivee == null){
            this.depart = d;
        }
        else {
            throw new IllegalArgumentException("La date de depart doit être avant celle d'arrivée");
        }
    }

    public void setDepart(String d){
        Date date = null;
        try {
            date = format.parse(d);
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        if (this.arrivee!=null && this.arrivee.compareTo(date)<0
        && date.compareTo(vol.getDateArrivee())<0 && date.compareTo(vol.getDateDepart())>0 ){
            this.arrivee = date;
        }
        else if (this.arrivee == null){
            this.depart = date;
        }
        else {
            throw new IllegalArgumentException("La date de depart doit être avant celle d'arrivée");
        }
    }

    public Date getDepart(){
        return this.depart;
    }

    public void setAeroport(Aeroport aeroport){
        if (aeroport != null){
            this.aeroport = aeroport;
        }
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

    protected void setVol(Vol v){
        this.vol = v;
    }
    /* 
    public String getInfosEscale(){
        return "Arrivee prévue : " + this.arrivee + " à " + this.aeroport.getNom() 
        + " puis départ prevus : " + this.depart +"\n";
    }
    */
}
