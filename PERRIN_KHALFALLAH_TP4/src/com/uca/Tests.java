package com.uca;

import com.uca.aeroport.Aeroport;
import com.uca.aeroport.Compagnie;
import com.uca.aeroport.Escale;
import com.uca.aeroport.Vol;
import com.uca.aeroport.Ville;
import com.uca.reservation.Client;
import com.uca.reservation.Passager;
import com.uca.reservation.Reservation;

import org.junit.jupiter.api.Test;

//import java.beans.Transient;
import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.text.SimpleDateFormat;

public class Tests {
    
    private static Aeroport a1;
    private static Aeroport a2;
    private static Vol vol1;
    private static Vol vol2;
    private static Compagnie cp1;
    private static Compagnie cp2;
    private static Client c1;
    private static Client c2;
    private static Passager p1;
    private static Passager p2;
    private static Ville ville1;
    private static Ville ville2;

    static{
        ville1 = new Ville("Paris");
        ville2 = new Ville("Clermont-Ferrand");

        a1 = new Aeroport("Charles de Gaulle");
        a1.addVille(ville1);
        
        a2 = new Aeroport("Aeroport d'Aulnat");
        a2.addVille(ville2);

        vol1 = new Vol(a1, a2);
        vol1.setNumero("V1");
        
        vol2 = new Vol(a2, a1);
        vol2.setNumero("V2");

        cp1 = new Compagnie("Air-France");
        
        cp2 = new Compagnie("France-Voyage");

        c1 = new Client("Frank");
        c1.setRef("Cl1");
        
        c2 = new Client("Thibault");
        c2.setRef("Cl2");

        p1 = new Passager("Frank");
        p2 = new Passager("Thomas");

    }

    @Test
    public void testsListeVolsCompagnie(){
        //test si la liste de vol de la compagnie est bien celle attendue
        cp1.addVol(vol1);
        cp1.addVol(vol2);
        Collection<Vol> Vols = new ArrayList<>();
        Vols.add(vol1);
        Vols.add(vol2);
        cp1.getVols().equals(Vols);
        //test si la liste de vol des aeroports sont les bonnes
        a1.getVols().equals(Vols);
        a2.getVols().equals(Vols);
        //On vérifie que la compagnie des vols est bien celle attendue
        assertThat(vol1.getCompagnie(), equalTo(cp1));
        assertThat(vol2.getCompagnie(), equalTo(cp1));
    }

    @Test
    public void testModifCompagnie(){
        //test si modifier la compagnie d'un vol fonctionne comme attendu
        cp1.addVol(vol1);
        vol1.setCompagnie(cp2);
        Collection<Vol> Vols = new ArrayList<>();
        //la compagnie 1 n'est plus censée avoir de vols
        cp1.getVols().equals(Vols);
        Vols.add(vol1);
        //la compagnie 2 est supposée avoir le vol 1
        cp2.getVols().equals(Vols);
    }

    @Test
    public void testSuppVolCompagnie(){
        //test si la suppression d'un vol dans une compagnie retire la compagnie du vol
        cp1.addVol(vol1);
        cp1.addVol(vol2);
        Collection<Vol> Vols = new ArrayList<>();
        Vols.add(vol2);
        cp1.removeVol(vol1);

        cp1.getVols().equals(Vols);
        assertThat(vol1.getCompagnie(), equalTo(null));
    }

    @Test
    public void testAddReservationClientEtVolEtPassager(){
        //test de l'ajout de la réservation au client, au vol, et au passager concerné lors de la création
        c1.addReservation(vol1, p1, "v1a");
        c1.addReservation(vol2, p1, "v2a");
        Collection<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(vol1, c1, p1, "v1a"));
        reservations.add(new Reservation(vol2, c1, p2, "v2a"));

        //On vérifie que la liste de reservation du client et du vol sont bien celles attendues
        c1.getReservations().equals(reservations);
        vol1.getReservations().equals(reservations);
        p1.getReservations().equals(reservations);
    }

    @Test
    public void testSuppReservationClientEtVolEtPassager(){
        //test de la suppression d'une réservation par le client et modif de la liste du vol et du passager concerné
        c1.addReservation(vol1, p1, "v1a");
        c1.addReservation(vol2, p1, "v2a");
        
        Collection<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(vol2, c1, p2, "v2a"));

        c1.removeReservation("v1a");

        //On vérifie que la liste de reservation du client et du vol sont bien celles attendues
        c1.getReservations().equals(reservations);
        vol1.getReservations().equals(reservations);
        p1.getReservations().equals(reservations);
    }

    @Test
    public void testValidationEtAnnulationReservation(){
        //on tets si la validation et l'annulation fonctionnent
        c1.addReservation(vol1, p1, "v1a");
        assertThat(c1.getReservation("v1a").getConfirmer(), equalTo(false));
        c1.confirmerReservation("v1a");
        assertThat(c1.getReservation("v1a").getConfirmer(), equalTo(true));
        c1.annulerReservation("v1a");
        assertThat(c1.getReservation("v1a").getConfirmer(), equalTo(false));
    }

    @Test
    public void testAnnulationVol(){
        //test si l'annulation d'un vol retire bien toutes ses réservations et modifie celles des clients et passagers
        c1.addReservation(vol1, p1, "v1a");
        c1.addReservation(vol2, p2, "v2a");
        c2.addReservation(vol1, p2, "v1b");

        vol1.annuler();

        Collection<Reservation> reservations = new ArrayList<>();
        Collection<Vol> vols = new ArrayList<>();
        vols.add(vol2);
        //Les deux aeroport sont supposés ne plus avoir le vol 1
        a1.getVols().equals(vols);
        a2.getVols().equals(vols);

        //Le client 2 est supposé avoir une liste vide
        c2.getReservations().equals(reservations);

        reservations.add(new Reservation(vol2, c1, p2, "v2a"));
        //Le client 1 est supposé avoir que la réservation de numero v2a
        c1.getReservations().equals(reservations);
        p1.getReservations().equals(reservations);
    }

    @Test
    public void testDatesVol(){
        //tets de fonctionnement des dates
        vol1.setDateDepart("21/10/2020 13:00");
        vol1.setDateArrivee("23/10/2020 05:15");
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            vol2.setDateDepart(format.parse("21/10/2020 13:00"));
            vol2.setDateArrivee(format.parse("23/10/2020 05:15"));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }
        //test de modification
        vol1.setDateArrivee("24/10/2020 07:28");
        vol1.setDateDepart("23/10/2020 08:37");

        //Je n'arrive pas à faire d'assertThat avec des instancesOf IllegalArgumentException
        //Car il faut que la méthode renvoie quelque chose si ce n'est pas une exception or les méthodes testées
        //retournent rien
        //Donc voilà des tests qui sont sensé lever une erreur. A tester individuelement évidemment (comme vous vous en doutez j'imagine)
        
        //vol1.setDateDepart("25/10/2020 07:28"); //Depart après arrivée
        //vol1.setDateArrivee("20/10/2020 08:37"); //Arrivee avant départ
        //vol1.setDateDepart("23/10/2020 07:4"); //format incorrect
    }

    @Test
    public void testEscale(){
        //test de set avec des String
        vol1.setDateDepart("21/10/2020 13:00");
        vol1.setDateArrivee("23/10/2020 05:15");

        Aeroport a3 = new Aeroport("ParisAeroport");
        Aeroport a4 = new Aeroport("LyonAeroport");
        Escale esc1 = new Escale(vol1, a3);
        Escale esc2 = new Escale(vol1, a4);
        //test de set des dates des escales
        esc1.setArrivee("22/10/2020 01:30");
        esc1.setDepart("22/10/2020 02:00");
        esc2.setArrivee("23/10/2020 00:00");
        esc2.setDepart("23/10/2020 00:30");

        Collection<Escale> escales = new ArrayList<>();
        escales.add(esc1);
        escales.add(esc2);
        //test si la liste des escales est celle attendue
        vol1.getEscales().equals(escales);
        assertThat(esc1.getAeroport(), equalTo(a3));
        assertThat(esc2.getAeroport(), equalTo(a4));
    }

    @Test
    public void testRemoveEscale(){
        //test de suppression de escales
        vol1.setDateDepart("21/10/2020 13:00");
        vol1.setDateArrivee("23/10/2020 05:15");

        Aeroport a3 = new Aeroport("Paris");
        Aeroport a4 = new Aeroport("Lyon");
        Escale esc1 = new Escale(vol1, a3);
        Escale esc2 = new Escale(vol1, a4);
        esc1.setArrivee("22/10/2020 01:30");
        esc1.setDepart("22/10/2020 02:00");
        vol1.removeEscale(esc2);

        Collection<Escale> escales = new ArrayList<>();
        escales.add(esc1);

        vol1.getEscales().equals(escales);
    }

    @Test
    public void testVillesAeroport(){
        //test des villes de a1
        Collection<Ville> villes = new ArrayList<>();
        villes.add(ville1);
        a1.getVilles().equals(villes);

        //test deux villes
        villes.add(ville2);
        a2.addVille(ville1);
        a2.getVilles().equals(villes);
        
        //test de suppression de ville
        villes.remove(ville1);
        a2.removeVille(ville1);
        a2.getVilles().equals(villes);

        //test liste de ville vide
        villes.remove(ville2);
        a2.removeVille(ville2);
        a2.getVilles().equals(villes);

        //Supposé ne rien faire
        a2.removeVille(ville1);
    }


    //Help you to handle exception. :-)
    public static Throwable exceptionOf(Callable<?> callable) {
        try {
            callable.call();
            return null;
        } catch (Throwable t) {
            return t;
        }
    }
}
