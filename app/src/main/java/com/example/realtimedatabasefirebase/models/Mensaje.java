package com.example.realtimedatabasefirebase.models;

public class Mensaje {

    private String texto;

    public Mensaje() {
    }
    public Mensaje(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
