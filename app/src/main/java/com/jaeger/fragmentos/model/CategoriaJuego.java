package com.jaeger.fragmentos.model;

public class CategoriaJuego {

    private int idCategoria;
    private String imagen;
    private  String nombreCategoria;

    public CategoriaJuego() {
    }

    public CategoriaJuego(int idCategoria, String imagen, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
