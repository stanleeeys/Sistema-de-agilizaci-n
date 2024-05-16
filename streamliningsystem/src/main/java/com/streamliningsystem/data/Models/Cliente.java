package com.streamliningsystem.data.Models;

public class Cliente {
    
    private int idCliente;
    
    private String encargadoCompra;
    
    private String nombreInstitucion;
    
    private String municipio;
    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEncargadoCompra() {
        return encargadoCompra;
    }

    public void setEncargadoCompra(String encargadoCompra) {
        this.encargadoCompra = encargadoCompra;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
}
