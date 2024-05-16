
package com.streamliningsystem.data.Models;


public class Cotizacion {
    private int id_cotizacion;
    private int cantidad;
    private String areaInversion;
    private String unidadMedida;
    private String descripcion;
    private double precioUnitario;
    private double precioTotal;

    public Cotizacion() {
    }

    public Cotizacion(int id_cotizacion, int cantidad, String areaInversion, String unidadMedida, String descripcion, double precioUnitario, double precioTotal) {
        this.id_cotizacion = id_cotizacion;
        this.cantidad = cantidad;
        this.areaInversion = areaInversion;
        this.unidadMedida = unidadMedida;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }

    public int getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(int id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAreaInversion() {
        return areaInversion;
    }

    public void setAreaInversion(String areaInversion) {
        this.areaInversion = areaInversion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}