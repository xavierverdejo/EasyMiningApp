package com.example.easyminingapp.objects;

public class Grafica {
    public long id_grafica;
    public String nom;
    public float hashrate;
    public byte[] imagte;

    public Grafica() {
    }
    public Grafica(String nom, float hashrate, byte[] imatge) {
        this.nom = nom;
        this.hashrate = hashrate;
        this.imagte = imatge;
    }

    public byte[] getImagte() {
        return imagte;
    }

    public void setImagte(byte[] imagte) {
        this.imagte = imagte;
    }

    public long getId_grafica() {
        return id_grafica;
    }

    public void setId_grafica(long id_grafica) {
        this.id_grafica = id_grafica;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getHashrate() {
        return hashrate;
    }

    public void setHashrate(float hashrate) {
        this.hashrate = hashrate;
    }
}
