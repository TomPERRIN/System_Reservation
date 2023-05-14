package com.uca.aeroport;

import java.util.ArrayList;
import java.util.Collection;

public class Compagnie {

    private String name;

    private Collection<Vol> vols = new ArrayList<>();


    public Compagnie(String nom) {
        setName(nom);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException{
        if (name == null){
            throw new IllegalArgumentException("Le nom de la compagnie ne peut pas être null");
        }
        this.name = name;
    }

    public Collection<Vol> getVols() {
        return vols;
    }

    public void setVols(Collection<Vol> vols) {
        for(Vol v : this.vols){
            v.setCompagnieWithoutBidirectional(null);
        }

        this.vols = vols;

        if(this.vols != null) {
            for (Vol v : this.vols) {
                v.setCompagnieWithoutBidirectional(this);
            }
        }
    }

    public void addVol(Vol vol){
        if (!this.vols.contains(vol)){
            vol.setCompagnieWithoutBidirectional(this);
            this.vols.add(vol);
        }
    }

    public void removeVol(Vol vol){
        if (this.vols.contains(vol)){
            vol.setCompagnieWithoutBidirectional(null);
            this.vols.remove(vol);
        }
    }

    public void removeAllVol(){
        for (Vol v : vols){
            v.setCompagnieWithoutBidirectional(null);
            this.vols.remove(v);
        }
    }

    public void annulerAllVols(){
        for (Vol v : vols){
            v.annuler();
        }
        this.vols.clear();
    }

    public void annulerVol(Vol v){
        if (this.vols.contains(v)){
            v.annuler();
        }
    }
    /* 
    public String getInfosCompagnie(){
        String infos = "Nom : " + this.name + "\nListe des vols : \n";
        for (Vol v : vols){
            infos += "Numero de vol : " + v.getNumero() + "\n";
        }
        return infos;
    }
    */

    protected void setVolsWithoutBidirectional(Collection<Vol> vols) {
        this.vols = vols;
    }

    protected void addVolWithoutBidirectional(Vol vol){
        this.vols.add(vol);
    }

    protected void removeVolWithoutBidirectional(Vol vol){
        this.vols.remove(vol);
    }

    
}
