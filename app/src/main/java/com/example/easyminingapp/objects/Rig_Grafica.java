package com.example.easyminingapp.objects;

public class Rig_Grafica {
    public long id_relacio;
    public long id_rig;
    public long id_grafica;

    public Grafica grafica;

    public long getId_relacio() {
        return id_relacio;
    }

    public void setId_relacio(long id_relacio) {
        this.id_relacio = id_relacio;
    }

    public long getId_rig() {
        return id_rig;
    }

    public void setId_rig(long id_rig) {
        this.id_rig = id_rig;
    }

    public long getId_grafica() {
        return id_grafica;
    }

    public void setId_grafica(long id_grafica) {
        this.id_grafica = id_grafica;
    }

    public float getHashrate_real() {
        return hashrate_real;
    }

    public void setHashrate_real(float hashrate_real) {
        this.hashrate_real = hashrate_real;
    }

    public float hashrate_real;

    public Grafica getGrafica() {
        return grafica;
    }

    public void setGrafica(Grafica grafica) {
        this.grafica = grafica;
    }
}
