package com.example.easyminingapp.objects;

import android.icu.lang.UProperty;

import java.util.ArrayList;

public class Rig {
    public long rig_id;
    public String nom;
    public String descripcio;
    public ArrayList<Rig_Grafica> grafiques;

    public Rig() {
    }
    public Rig(String nom, String descripcio) {
        this.nom = nom;
        this.descripcio = descripcio;
    }

    public long getRig_id() {
        return rig_id;
    }

    public void setRig_id(long rig_id) {
        this.rig_id = rig_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public ArrayList<Rig_Grafica> getGrafiques() {
        return grafiques;
    }

    public void setGrafiques(ArrayList<Rig_Grafica> grafiques) {

        this.grafiques = grafiques;
    }
}
