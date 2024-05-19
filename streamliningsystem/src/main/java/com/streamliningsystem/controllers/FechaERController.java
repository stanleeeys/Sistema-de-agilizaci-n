package com.streamliningsystem.controllers;

import com.streamliningsystem.data.DAO.FechaErDAO;
import com.streamliningsystem.data.Models.FechasER;
import com.streamliningsystem.models.ViewModels.FechaErVM;

public class FechaERController {

    // Objetos Globales
    FechaErDAO fechaErDAO = new FechaErDAO();

    //Metodo de insercion de fechas
    public boolean guardarFechas(FechaErVM fechaErVM) {

        FechasER fechasER = new FechasER();
        fechasER.setFechaSolicitud(fechaErVM.fechaSolicitud);
        fechasER.setFechaCotizacion(fechaErVM.fechaCotizacion);
        fechasER.setFechaOrden(fechaErVM.fechaOrden);
        fechasER.setFechaRecepcion(fechaErVM.fechaRecepcion);
        fechasER.setFechaPlanCompras(fechaErVM.fechaPlanCompras);

        return fechaErDAO.ingresarFechas(fechasER);
    }
    
    public boolean actualizarFechas(FechaErVM fechaErVM) {

        FechasER fechasER = new FechasER();
        fechasER.setIdFechas(fechaErVM.idFechas);
        fechasER.setFechaSolicitud(fechaErVM.fechaSolicitud);
        fechasER.setFechaCotizacion(fechaErVM.fechaCotizacion);
        fechasER.setFechaOrden(fechaErVM.fechaOrden);
        fechasER.setFechaRecepcion(fechaErVM.fechaRecepcion);
        fechasER.setFechaPlanCompras(fechaErVM.fechaPlanCompras);

        return fechaErDAO.ingresarFechas(fechasER);
    }
    
    
    
    public int obtenerFecha(){
    
        return fechaErDAO.obtenerFecha();
    }
}
