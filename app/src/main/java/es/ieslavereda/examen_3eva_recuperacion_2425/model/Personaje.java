package es.ieslavereda.examen_3eva_recuperacion_2425.model;

import java.io.Serializable;

public class Personaje implements Serializable {

    private String nombre;
    private Casa casa;

    public Personaje(String nombre, Casa casa) {
        this.nombre = nombre;
        this.casa = casa;
    }

    public String getNombre() {
        return nombre;
    }

    public Casa getCasa() {
        return casa;
    }
}
