package com.streamliningsystem.models.ViewModels;

public class OrdenVM {

    public int idOrden;

    public String codOrden;

    public String encargadoOrden;

    public double totales;

    public int clienteId;

    public int proveedorId;

    public int fechasErId;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
    }

    public String getEncargadoOrden() {
        return encargadoOrden;
    }

    public void setEncargadoOrden(String encargadoOrden) {
        this.encargadoOrden = encargadoOrden;
    }

    public double getTotales() {
        return totales;
    }

    public void setTotales(double totales) {
        this.totales = totales;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public int getFechasErId() {
        return fechasErId;
    }

    public void setFechasErId(int fechasErId) {
        this.fechasErId = fechasErId;
    }
}
