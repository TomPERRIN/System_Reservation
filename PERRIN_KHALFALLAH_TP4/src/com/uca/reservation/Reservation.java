package com.uca.reservation;
import com.uca.aeroport.*;

public class Reservation {
    private Vol vol; 
    private Client client;
    private Passager passager;
    private String numero;
    private boolean confirmer;
    
    public Reservation(Vol vol, Client client, Passager passager, String numero) throws IllegalArgumentException{
        if (vol == null || client == null || passager == null){
            throw new IllegalArgumentException("une réservation doit avoir un vol, un client et un passager existants pour être créer");
        }
        this.numero = numero;
        this.client = client;
        this.passager = passager;
        this.vol = vol;
        this.confirmer = false;
        vol.addReservation(this);
        passager.addReservation(this);
    }

    public Client getClient() {
        return client;
    }

    public String getNumero(){
        return this.numero;
    }
    
    public void setNumero(String num){
        this.numero = num;
    }

    public Passager getPassager(){
        return this.passager;
    }

    protected void setPassager(Passager passager) throws IllegalArgumentException{
        if (passager == null){
            throw new IllegalArgumentException("Un passager ne doit pas être null");
        }
        this.passager.removeReservation(this);
        this.passager = passager;
        passager.addReservation(this);
    }
    
    public Vol getVol(){
        return this.vol;
    }
    
    public boolean getConfirmer(){
        return this.confirmer;
    }

    protected void confirmer(){
        this.confirmer = true;
    }

    protected void annuler(){
        this.confirmer = false;
    }

    //On différencie la suppression par le vol ou le client pour éviter les boucles infinies et les erreurs
    public void supprimerFromVol(){
        this.client.removeReservationFromVol(numero);
        this.passager.removeReservation(this);
    }

    protected void supprimerFromClient(){
        this.vol.removeReservationFromClient(numero);
        this.passager.removeReservation(this);
    }
    /* 
    public String getInfosReservation(){
        return "  Numero de reservation : " + this.getNumero() + "\n  Numero de vol : " + this.vol.getNumero() + 
        "\n  Client : "+ this.client.getNom() + "\n  Passager : " + this.passager.getNom() + "\nConfirmer : " + this.confirmer;
    }
    */
}
