package com.uca.aeroport;

import java.util.ArrayList;
import java.util.Collection;

public class Aeroport {

    private String nom;

    private String ville;

    private Collection<Vol> vols = new ArrayList<>();

    public Aeroport() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Collection<Vol> getVols() {
        return vols;
    }

    public void setVols(Collection<Vol> vols) {
        for(Vol v : this.vols){
            v.setDepart(null);
        }

        this.vols = vols;

        if(this.vols != null) {
            for (Vol v : this.vols) {
                v.setDepart(this);
            }
        }
    }

    protected void addVol(Vol vol){
        //vol.setDepart(this);
        this.vols.add(vol);
    }

    protected void removeVol(Vol vol){
        //vol.setDepart(null);
        this.vols.remove(vol);
    }

    public String getInfosAeroport(){
        String infos = "Nom : " + this.nom + "\nDessert : " + this.ville + "\nListe des vols :\n";
        for (Vol v : this.vols){
            infos += v.getNumero() + "\n";
        }
        return infos;
    }
}
