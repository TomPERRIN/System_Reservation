package com.uca.aeroport;
import com.uca.reservation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Vol {

    private String numero;

    private Aeroport depart;

    private Aeroport arrivee;

    private Compagnie compagnie;

    private Date dateDepart = null;

    private Date dateArrivee = null;

    private boolean ouvert;

    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

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

    public void setDateDepart(Date dateDepart) throws IllegalArgumentException{
        if (this.dateArrivee != null && dateDepart.compareTo(this.dateArrivee)<0){
            this.dateDepart = dateDepart;
        }
        else if (this.dateArrivee == null){
            this.dateDepart = dateDepart;
        }
        else {
            throw new IllegalArgumentException("La date de depart doit être avant celle d'arrivée");
        }
    }

    public void setDateDepart(String dateDepart){
        Date date = null;
        try {
            date = format.parse(dateDepart);
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        if (this.dateArrivee!=null && date.compareTo(this.dateArrivee)<0){
            this.dateDepart = date;
        }
        else if (this.dateArrivee == null){
            this.dateDepart = date;
        }
        else {
            throw new IllegalArgumentException("La date de depart doit être avant celle d'arrivée");
        }
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) throws IllegalArgumentException {
        if (this.dateDepart!=null && this.dateDepart.compareTo(dateArrivee)<0){
            this.dateArrivee = dateArrivee;
        }
        else if (this.dateDepart == null){
            this.dateArrivee = dateArrivee;
        }
        else {
            throw new IllegalArgumentException("La date d'arrivée doit être après celle de départ");
        }
    }

    public void setDateArrivee(String dateArrivee){
        Date date = null;
        try {
            date = format.parse(dateArrivee);
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        if (this.dateDepart!=null && this.dateDepart.compareTo(date)<0){
            this.dateArrivee = date;
        }
        else if (this.dateDepart == null){
            this.dateArrivee = date;
        }
        else {
            throw new IllegalArgumentException("La date d'arrivée doit être après celle de départ");
        }
    }
    //pas en protected car un vol peut être créer sans compagnie
    public Vol(Aeroport depart, Aeroport arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.depart.addVol(this);
        this.arrivee.addVol(this);
        this.ouvert = true;
    }

    protected Vol(Aeroport depart, Aeroport arrivee, String numero){
        this.depart = depart;
        this.arrivee = arrivee;
        this.depart.addVol(this);
        this.arrivee.addVol(this);
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

    public void setDepart(Aeroport depart) throws IllegalArgumentException {
        if (depart == null){
            throw new IllegalArgumentException("Il doit y avoir un aéroport de départ");
        }
        this.depart = depart;
        this.depart.addVol(this);
    }

    public Aeroport getArrivee() {
        return arrivee;
    }

    public void setArrivee(Aeroport arrivee) throws IllegalArgumentException {
        if (arrivee == null){
            throw new IllegalArgumentException("Il doit y avoir un aéroport de départ");
        }
        
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
        if(this.ouvert && (!this.reservations.contains(reservation))){
            this.reservations.add(reservation);
        }
    }

    public void removeReservationFromClient(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero && this.reservations.contains(r)){
                this.reservations.remove(r);
                break;
            }
        }
    }

    public void removeReservation(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero && this.reservations.contains(r)){
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
        }
        this.reservations.clear();
        depart.removeVol(this);
        arrivee.removeVol(this);
    }

    public void addEscale(Escale esc){
        if (!this.escales.contains(esc)){
            this.escales.add(esc);
        }
    }

    public void removeEscale(Escale esc){
        if (this.escales.contains(esc)){
            esc.setVol(null);
            this.escales.remove(esc);
        }
    }

    public Collection<Escale> getEscales(){
        return this.escales;
    }
    /* 
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
    */
    
    @Override
    public boolean equals(Object obj) {
        try {
            return ((Vol) obj).getNumero().equals(this.numero);
        } catch (Exception e){
            return false;
        }
    }
}
