package com.streamliningsystem.models.ViewModels;

import java.util.Date;

public class FechaErVM {
    
    
    public int idFechas;

    public Date fechaSolicitud;

    public Date fechaCotizacion;

    public Date fechaOrden;

    public Date fechaRecepcion;

    public Date fechaPlanCompras;

    public int getIdFechas() {
        return idFechas;
    }

    public void setIdFechas(int idFechas) {
        this.idFechas = idFechas;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(Date fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaPlanCompras() {
        return fechaPlanCompras;
    }

    public void setFechaPlanCompras(Date fechaPlanCompras) {
        this.fechaPlanCompras = fechaPlanCompras;
    }  
    
}
