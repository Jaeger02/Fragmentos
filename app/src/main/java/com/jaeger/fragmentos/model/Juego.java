package com.jaeger.fragmentos.model;

public class Juego {

    private int idJuego;
    private String imagen;
    private String titulo;
    private int clasificacion;
    private String descripcion;

    public Juego() {
    }

    public Juego(int idJuego, String imagen, String titulo, int clasificacion, String descripcion) {
        this.idJuego = idJuego;
        this.imagen = imagen;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
