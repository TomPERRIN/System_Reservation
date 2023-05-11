package com.uca;

import com.uca.*;
import com.uca.aeroport.Aeroport;
import com.uca.aeroport.Compagnie;
import com.uca.aeroport.Vol;
import com.uca.reservation.Client;
import com.uca.reservation.Passager;

import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Tests {
    
    private Aeroport a1;
    private Aeroport a2;
    private Vol vol1;
    private Vol vol2;
    private Compagnie cp1;
    private Compagnie cp2;
    private Client c1;
    private Client c2;
    private Passager p1;
    private Passager p2;

    public Tests (){
        this.a1 = new Aeroport();
        a1.setNom("Charles de Gaulle");
        a1.setVille("Paris");
        this.a2 = new Aeroport();
        a2.setNom("Aeroport d'Aulnat");
        a2.setVille("Clermont-Ferrand");

        this.vol1 = new Vol();
        this.vol2 = new Vol();
        vol1.setNumero("V1");
        vol2.setNumero("V2");

        this.cp1 = new Compagnie();
        cp1.setName("Air-France");
        this.cp2 = new Compagnie();
        cp2.setName("France-Voyage");

        this.c1 = new Client("Frank");
        this.c2 = new Client("Thibault");
        c1.setRef("Cl1");
        c2.setRef("Cl2");

        this.p1 = new Passager("Frank");
        this.p2 = new Passager("Thomas");

    }

    @Test
    public void testsListeVolsCompagnie(){
        
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
