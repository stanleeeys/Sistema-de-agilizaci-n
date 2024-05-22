package ViewModel;

import java.util.Date;

public class OrdenVM {

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

    public Date getLimite_cotizacion() {
        return limite_cotizacion;
    }

    public void setLimite_cotizacion(Date limite_cotizacion) {
        this.limite_cotizacion = limite_cotizacion;
    }

    public Date getFecha_de_entrega() {
        return fecha_de_entrega;
    }

    public void setFecha_de_entrega(Date fecha_de_entrega) {
        this.fecha_de_entrega = fecha_de_entrega;
    }

    public String getHora_entrega_desde() {
        return hora_entrega_desde;
    }

    public void setHora_entrega_desde(String hora_entrega_desde) {
        this.hora_entrega_desde = hora_entrega_desde;
    }

    public String getHora_entrega_hasta() {
        return hora_entrega_hasta;
    }

    public void setHora_entrega_hasta(String hora_entrega_hasta) {
        this.hora_entrega_hasta = hora_entrega_hasta;
    }

    public String getTiempo_entrega() {
        return tiempo_entrega;
    }

    public void setTiempo_entrega(String tiempo_entrega) {
        this.tiempo_entrega = tiempo_entrega;
    }

    public String getPlazo_entrega() {
        return plazo_entrega;
    }

    public void setPlazo_entrega(String plazo_entrega) {
        this.plazo_entrega = plazo_entrega;
    }

    public String getLugar_entrega() {
        return lugar_entrega;
    }

    public void setLugar_entrega(String lugar_entrega) {
        this.lugar_entrega = lugar_entrega;
    }

    public String getVigencia_de_la_cotizacion() {
        return vigencia_de_la_cotizacion;
    }

    public void setVigencia_de_la_cotizacion(String vigencia_de_la_cotizacion) {
        this.vigencia_de_la_cotizacion = vigencia_de_la_cotizacion;
    }

    public String getTiempo_de_garantia() {
        return tiempo_de_garantia;
    }

    public void setTiempo_de_garantia(String tiempo_de_garantia) {
        this.tiempo_de_garantia = tiempo_de_garantia;
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

    public int idOrden;

    public String codOrden;

    public String encargadoOrden;

    public double totales;
    
    public Date limite_cotizacion;
    
    public Date fecha_de_entrega;
    
    public String hora_entrega_desde;
    
    public String hora_entrega_hasta;
    
    public String tiempo_entrega; 
    
    public String plazo_entrega; 
    
    public String lugar_entrega; 
    
    public String vigencia_de_la_cotizacion; 
    
    public String tiempo_de_garantia;

    public int clienteId;

    public int proveedorId;

    public int fechasErId;

    
}
