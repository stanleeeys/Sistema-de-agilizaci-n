
package Models;

import java.util.Date;

public class FechasER {
    
    private int idFechas;
    
    private Date fechaSolicitud;
    
    private Date fechaCotizacion;
    
    private Date fechaOrden;

    private Date fechaRecepcion;
    
    private Date fechaPlanCompras;

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
