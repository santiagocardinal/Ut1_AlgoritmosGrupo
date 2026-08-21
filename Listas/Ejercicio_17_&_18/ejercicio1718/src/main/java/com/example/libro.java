package com.example;

public class libro 
{
    private String codigoIdentificacion;
    private String titulo;
    private Double precio;
    private int cantidadEjemplares;

    public libro(String codigoIdentificacion, String titulo, Double precio, int cantidadEjemplares) {
        this.codigoIdentificacion = codigoIdentificacion;
        this.titulo = titulo;
        this.precio = precio;
        this.cantidadEjemplares = cantidadEjemplares;
    }
    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }
    public void setCodigoIdentificacion(String codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }
    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
