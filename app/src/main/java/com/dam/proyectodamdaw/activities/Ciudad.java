package com.dam.proyectodamdaw.activities;

import java.io.Serializable;

public class Ciudad implements Serializable {
    private String nombre;
    private double lon;
    private double lat;
    private String imagen;

    public Ciudad(String nombre,double lat,double lon,String imagen){
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getImagen() {
        return imagen;
    }

    public String toString(){
        return  nombre+"";
    }
}
