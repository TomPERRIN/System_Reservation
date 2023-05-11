package com.uca.aeroport;
import com.uca.reservation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Vol {

    private String numero;

    private Aeroport depart;

    private Aeroport arrivee;

    private Compagnie compagnie;

    private Date dateDepart;

    private Date dateArrivee;

    private boolean ouvert;

    private Collection<Reservation> reservations = new ArrayList<>();

    private Collection<Escale> escales = new ArrayList<>();

    public Duration obtenirDuree() {
        if(this.dateDepart != null && this.dateArrivee != null) {
            return Duration.of(dateArrivee.getTime() - dateDepart.getTime(), ChronoUnit.MILLIS);
        }
        return null;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Vol() {
        this.ouvert = true;
    }

    protected Vol(String numero){
        this.numero = numero;
        this.ouvert = true;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie.removeVolWithoutBidirectional(this);
        if(compagnie!=null) {
            compagnie.addVolWithoutBidirectional(this);
        }
        else {
            this.compagnie.removeVolWithoutBidirectional(this);
        }
        this.compagnie = compagnie;
    }

    protected void setCompagnieWithoutBidirectional(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Aeroport getDepart() {
        return depart;
    }

    public void setDepart(Aeroport depart) {
        this.depart = depart;
        this.depart.addVol(this);
    }

    public Aeroport getArrivee() {
        return arrivee;
    }

    public void setArrivee(Aeroport arrivee) {
        this.arrivee = arrivee;
        this.arrivee.addVol(this);
    }

    public boolean getOuvert(){
        return this.ouvert;
    }

    public void setOuvert(boolean ouvert){
        this.ouvert = ouvert;
    }
    
    public Collection<Reservation> getReservations(){
        return this.reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        for(Reservation r : reservations){
            if(this.ouvert){
                this.reservations.add(r);
            }
        }
    }

    public void addReservation(Reservation reservation){
        if(this.ouvert){
            this.reservations.add(reservation);
        }
    }

    public void removeReservationFromClient(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                this.reservations.remove(r);
                break;
            }
        }
    }

    public void removeReservation(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                r.supprimerFromVol();
                this.reservations.remove(r);
                break;
            }
        }
    }

    public void annuler(){
        for (Reservation r : this.reservations){
            r.supprimerFromVol();
            depart.removeVol(this);
            this.reservations.remove(r);
        }
        depart.removeVol(this);
        arrivee.removeVol(this);
    }

    public void setEscales(Collection<Escale> esc) {
        this.escales = esc;
    }

    public void addEscale(Escale esc){
        this.escales.add(esc);
    }

    public void removeEscale(Escale esc){
        this.escales.remove(esc);
    }

    public Collection<Escale> getEscales(){
        return this.escales;
    }

    public String getInfosVol(){
        String infos = "Numero : " + this.numero + "\nCompagnie : " + this.compagnie.getName() + "\nOuvert : " + this.ouvert
        + "\nDepart de " + depart.getNom() + " le " + dateDepart.toString() + "\nArrivee à " + arrivee.getNom() + " le " 
        + dateArrivee.toString() + "\nDuree totale =" + obtenirDuree().toString() + "\nEscales : \n";
        for (Escale e : this.escales){
            infos += e.getInfosEscale();
        }
        infos += "\nListe de reservation :\n";
        for (Reservation r : this.reservations){
            infos += r.getInfosReservation() + "\n\n";
        }
        return infos;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            return ((Vol) obj).getNumero().equals(this.numero);
        } catch (Exception e){
            return false;
        }
    }
}
