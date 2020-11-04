package com.jaeger.fragmentos.model;

public class AdministrarJuego {

    private int idMiJuego;
    private String imagen;
    private String titulo;
    private int clasificacion;
    private String Descripcion;

    public AdministrarJuego() {
    }

    public AdministrarJuego(int idMiJuego, String imagen, String titulo, int clasificacion, String descripcion) {
        this.idMiJuego = idMiJuego;
        this.imagen = imagen;
        this.titulo = titulo;
        this.clasificacion = clasificacion;
        Descripcion = descripcion;
    }

    public int getIdMiJuego() {
        return idMiJuego;
    }

    public void setIdMiJuego(int idMiJuego) {
        this.idMiJuego = idMiJuego;
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
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
