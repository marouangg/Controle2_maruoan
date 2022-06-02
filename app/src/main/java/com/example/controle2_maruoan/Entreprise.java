package com.example.controle2_maruoan;

public class Entreprise {

    int id ;
    String raison_social ;
    String adress;
    double capital;

    public Entreprise() {

    }
    public Entreprise(int id, String raison_social, String adress, double capital) {
        this.id = id;
        this.raison_social = raison_social;
        this.adress = adress;
        this.capital = capital;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRaison_social(String raison_social) {
        this.raison_social = raison_social;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public int getId() {
        return id;
    }

    public String getRaison_social() {
        return raison_social;
    }

    public String getAdress() {
        return adress;
    }

    public double getCapital() {
        return capital;
    }
}
