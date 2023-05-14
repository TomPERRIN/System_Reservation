package com.uca.aeroport;

import java.util.ArrayList;
import java.util.Collection;

public class Aeroport {

    private String nom;

    private Collection<Vol> vols = new ArrayList<>();
    private Collection<Ville> villes = new ArrayList<>();

    public Aeroport(String nom) {
        this.setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws IllegalArgumentException{
        if (nom == null ){
            throw new IllegalArgumentException("Nom de l'aeroport ne peut pas être null");
        }
        this.nom = nom;
    }

    public Collection<Ville> getVilles() {
        return this.villes;
    }

    public void addVille(Ville ville) {
        if (!this.villes.contains(ville)){
            this.villes.add(ville);
        }
    }

    public void removeVille(Ville ville){
        if (this.villes.contains(ville)){
            this.villes.remove(ville);
        }
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
        if (!this.vols.contains(vol)){
            this.vols.add(vol);
        }
    }

    protected void removeVol(Vol vol){
        if (this.vols.contains(vol)){
            this.vols.remove(vol);
        }
    }
    /*
    public String getInfosAeroport(){
        String infos = "Nom : " + this.nom + "\nDessert : ";
        for (Ville v : this.villes){
            infos += v.getNom()+"\n";
        }
        infos += "\nListe des vols :\n";
        for (Vol v : this.vols){
            infos += v.getNumero() + "\n";
        }
        return infos;
    }
    */
}
