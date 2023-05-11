package com.uca.reservation;
import java.util.ArrayList;
import java.util.Collection;
import com.uca.aeroport.*;

public class Client {
    private String nom;
    private String ref;
    private Collection<Reservation> reservations = new ArrayList<>();

    public Client(String nom){
        this.setNom(nom);
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getRef(){
        return this.ref;
    }

    public void setRef(String ref){
        this.ref = ref;
    }

    public Collection<Reservation> getReservations(){
        return this.reservations;
    }

    public Reservation getReservation(String num){
        for (Reservation r : reservations){
            if (r.getNumero() == num){
                System.out.println("test1A");
                return r;
            }
        }
        System.out.println("Cette reservation n'existe pas ou le vol est fermé");
        return null;
    }
     
    public void setReservations(Collection<Reservation> reservations) {
        for(Reservation r : reservations){
            if(r.getVol().getOuvert()){
                this.reservations.add(r);
            }
        }
    }
    
    
    public void addReservation(Vol vol, Passager passager, String numero){
        Reservation r = new Reservation(vol, this, passager, numero);
        this.reservations.add(r);
    }
    
    public void removeReservation(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                this.reservations.remove(r);
                r.supprimerFromClient();;
                break;
            }
        }
    }

    public void removeReservationFromVol(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                this.reservations.remove(r);
                break;
            }
        }
    }

    public void confirmerReservation(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                r.confirmer();
                break;
            }
        }
    }

    public void annulerReservation(String numero){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                r.annuler();
                break;
            }
        }
    }

    public void modifPassagerReservation(String numero, Passager passager){
        for(Reservation r : this.reservations){
            if (r.getNumero() == numero){
                r.setPassager(passager);
                break;
            }
        }
    }

    public String getInfosClient(){
        String infos = "Nom : " + this.nom + "\nRef : " + this.ref + "\nListe de reservation :\n";
        for (Reservation r : this.reservations){
            infos += r.getInfosReservation() + "\n\n";
        }
        return infos;
    }
}
