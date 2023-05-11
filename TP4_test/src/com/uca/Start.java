package com.uca;
import com.uca.aeroport.*;
import com.uca.reservation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;


public class Start {

    public static void main(String[] args){
        Vol volFinal = new Vol();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dd = "21/10/2020 13:00";
        String da = "23/10/2020 05:15";
        String dd2 = "14/02/2020 13:00";
        String da2 = "14/02/2020 22:22";

        try {
            volFinal.setDateDepart(format.parse(dd));
            volFinal.setDateArrivee(format.parse(da));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        System.out.println(volFinal.getDateArrivee());
        System.out.println(volFinal.obtenirDuree().toString().substring(2));


        //Bidirectional
        Vol vol = new Vol();
        Vol vol2 = new Vol();

        vol.setNumero("abc1");
        vol2.setNumero("abc2");

        try {
            vol.setDateDepart(format.parse(dd));
            vol.setDateArrivee(format.parse(da));
            vol2.setDateDepart(format.parse(dd2));
            vol2.setDateArrivee(format.parse(da2));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        Compagnie compagnie = new Compagnie();
        compagnie.setName("Air France");
        compagnie.addVol(vol);
        compagnie.addVol(vol2);

        Aeroport Paris = new Aeroport();
        Aeroport Lyon = new Aeroport();
        Aeroport Otawa = new Aeroport();
        Aeroport Londre = new Aeroport();

        Paris.setNom("Nom_Paris");
        Lyon.setNom("Nom_Lyon");
        Otawa.setNom("Nom_Otawa");
        Londre.setNom("Nom_Londre");
        
        Paris.setVille("Paris");
        Lyon.setVille("Lyon");
        Otawa.setVille("Otawa");
        Londre.setVille("Londre");

        vol.setDepart(Otawa);
        vol.setArrivee(Londre);
        vol2.setDepart(Paris);
        vol2.setArrivee(Lyon);

        Escale escale1 = new Escale();
        Escale escale2 = new Escale();
        escale1.setAeroport(Lyon);
        escale2.setAeroport(Paris);

        try {
            escale1.setArrivee(format.parse("22/10/2020 9:00"));
            escale1.setDepart(format.parse("22/10/2020 10:00"));
            escale2.setArrivee(format.parse("23/10/2020 2:00"));
            escale2.setDepart(format.parse("23/10/2020 2:30"));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        vol.addEscale(escale1);
        vol.addEscale(escale2);

        System.out.println("Aeroports : ");
        System.out.println(Paris.getInfosAeroport());
        System.out.println(Lyon.getInfosAeroport());
        System.out.println(Otawa.getInfosAeroport());
        System.out.println(Londre.getInfosAeroport());

        System.out.println("Infos de la compagnie \n" + compagnie.getInfosCompagnie() + "\n\n");
        
        System.out.println("Les vols : ");
        System.out.println(vol.getInfosVol());
        System.out.println(vol2.getInfosVol());

        /*
        System.out.println("Compagnie du vol 2 après avoir set sa compagnie à null");
        vol2.setCompagnie(null);
        System.out.println(vol2.getCompagnie());

        System.out.println("Nouvelles infos de la compagnie : ");
        compagnie.getInfosCompagnie();
        */
/*==========================================================================================*/

        /* 
        vol.setOuvert(true);
        vol2.setOuvert(true);

        vol.setCompagnie(compagnie);
        vol2.setCompagnie(compagnie);

        Client client1 = new Client("Joseph");
        Client client2 = new Client("Frank");
        Client client3 = new Client("Augustin");

        client1.setRef("c1a");
        client2.setRef("c1b");
        client3.setRef("c1c");

        Passager passager1 = new Passager("Joseph");
        Passager passager2 = new Passager("Hélio");        
        Passager passager3 = new Passager("Pedro");


        client3.addReservation(vol, passager1, "v1a");
        client2.addReservation(vol, passager2, "v1b");
        client2.addReservation(vol, passager3, "v1c");
        client1.addReservation(vol2, passager1, "v2a");
        client3.addReservation(vol2, passager3, "v2b");

        System.out.println("\nInformations du vol 1");
        System.out.println(vol.getInfosVol());

        System.out.println("\nInformations du vol 2");
        System.out.println(vol2.getInfosVol());

        System.out.println("\nInformations du client 1 :");
        System.out.println(client1.getInfosClient());

        System.out.println("\nInformations du client 2 :");
        System.out.println(client2.getInfosClient());

        System.out.println("\nInformations du client 3 :");
        System.out.println(client3.getInfosClient());
        */

        //tests d'annulation de vol
        /* 
        System.out.println("\nOn annule les 2 vols");
        vol2.annuler();
        vol.annuler();

        System.out.println("\nInformations du client 1 :");
        System.out.println(client1.getInfosClient());

        System.out.println("\nInformations du client 2 :");
        System.out.println(client2.getInfosClient());

        System.out.println("\nInformations du client 3 :");
        System.out.println(client3.getInfosClient());
        */
        
        //test de modification des passagers
        /* 
        System.out.println("\nModification des passagers");
        client1.modifPassagerReservation("v2a", new Passager(("Christian")));
        client3.modifPassagerReservation("v2b", new Passager(("Thomas")));
        client2.modifPassagerReservation("v1b", new Passager(("Willi")));
        
        System.out.println("\nInformations du client 1 :");
        System.out.println(client1.getInfosClient());

        System.out.println("\nInformations du client 2 :");
        System.out.println(client2.getInfosClient());

        System.out.println("\nInformations du client 3 :");
        System.out.println(client3.getInfosClient());
        */

        //test de suppression de reservations par le client
        /*
        System.out.println("suppression de reservations par le client");

        client1.removeReservation("v2a");
        client2.removeReservation("v1b");
        client3.removeReservation("v1a");

        System.out.println("\nInformations du client 1 :");
        System.out.println(client1.getInfosClient());

        System.out.println("\nInformations du client 2 :");
        System.out.println(client2.getInfosClient());

        System.out.println("\nInformations du client 3 :");
        System.out.println(client3.getInfosClient());

        System.out.println("\nInformations du vol 1");
        System.out.println(vol.getInfosVol());

        System.out.println("\nInformations du vol 2");
        System.out.println(vol2.getInfosVol());
        */

        //test de suppression de reservations par les vols
        /*
        System.out.println("suppression de reservations par les vols");

        vol2.removeReservation("v2a");
        vol.removeReservation("v1b");
        vol.removeReservation("v1a");

        System.out.println("\nInformations du client 1 :");
        System.out.println(client1.getInfosClient());

        System.out.println("\nInformations du client 2 :");
        System.out.println(client2.getInfosClient());

        System.out.println("\nInformations du client 3 :");
        System.out.println(client3.getInfosClient());

        System.out.println("\nInformations du vol 1");
        System.out.println(vol.getInfosVol());

        System.out.println("\nInformations du vol 2");
        System.out.println(vol2.getInfosVol());
        */

        //test de confirmation
        /* 
        System.out.println("confirmation des vols du client 2");
        client2.confirmerReservation("v1b");
        client2.confirmerReservation("v1c");

        System.out.println("\nInformations du client 2 :");
        System.out.println(client2.getInfosClient());

        System.out.println("Annulation de la reservation v1b du client 2");
        client2.annulerReservation("v1b");

        System.out.println("\nNouvelles informations du client 2 :");
        System.out.println(client2.getInfosClient());
        */
    }
}
