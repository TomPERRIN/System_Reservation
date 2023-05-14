//PERRIN Tom 
//KHALFALLAH Elyes
package com.uca;
import com.uca.aeroport.*;
import java.text.SimpleDateFormat;

//J'aurais pu condenser certaines parties (surtout les vérifications des dates dans les escales et les vols)

public class Start {

    public static void main(String[] args){
        Vol volFinal = new Vol(new Aeroport("Charles de Gaulle"), new Aeroport("Nancy Aeroport"));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dd = "21/10/2020 13:00";
        String da = "23/10/2020 05:15";

        try {
            volFinal.setDateDepart(format.parse(dd));
            volFinal.setDateArrivee(format.parse(da));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        System.out.println(volFinal.getDateArrivee());
        System.out.println(volFinal.obtenirDuree().toString().substring(2));
    }
}
