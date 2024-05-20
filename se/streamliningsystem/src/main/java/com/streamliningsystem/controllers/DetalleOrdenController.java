package com.streamliningsystem.controllers;

import com.streamliningsystem.data.DAO.DetalleOrdenDAO;
import com.streamliningsystem.data.Models.DetalleOrden;
import com.streamliningsystem.models.ViewModels.DetalleOrdenVM;
import java.util.ArrayList;

public class DetalleOrdenController {

    // Objetos Globales
    DetalleOrdenDAO ordenDetalleDAO = new DetalleOrdenDAO();

    //Metodo de insercion de fechas
    public boolean guardarDetalleOrden(DetalleOrdenVM ordenDetalleVM) {

        DetalleOrden ordenDetalle = new DetalleOrden();
        ordenDetalle.setNumArticulo(ordenDetalleVM.numArticulo);
        ordenDetalle.setCantidad(ordenDetalleVM.cantidad);
        ordenDetalle.setUnidadMedida(ordenDetalleVM.unidadMedida);
        ordenDetalle.setDescripcionArticulo(ordenDetalleVM.descripcionArticulo);
        ordenDetalle.setPrecioUnitario(ordenDetalleVM.precioUnitario);
        ordenDetalle.setPrecioTotal(ordenDetalleVM.precioTotal);
        ordenDetalle.setOrdenId(ordenDetalleVM.ordenId);

        return ordenDetalleDAO.ingresarDetalleOrden(ordenDetalle);
    }
    
    public boolean actualizarDetalleOrden(DetalleOrdenVM ordenDetalleVM) {

        DetalleOrden ordenDetalle = new DetalleOrden();
        ordenDetalle.setIdDetalleOrden(ordenDetalleVM.idDetalleOrden);
        ordenDetalle.setNumArticulo(ordenDetalleVM.numArticulo);
        ordenDetalle.setCantidad(ordenDetalleVM.cantidad);
        ordenDetalle.setUnidadMedida(ordenDetalleVM.unidadMedida);
        ordenDetalle.setDescripcionArticulo(ordenDetalleVM.descripcionArticulo);
        ordenDetalle.setPrecioUnitario(ordenDetalleVM.precioUnitario);
        ordenDetalle.setPrecioTotal(ordenDetalleVM.precioTotal);
        ordenDetalle.setOrdenId(ordenDetalleVM.ordenId);

        return ordenDetalleDAO.actualizarDetalleOrden(ordenDetalle);
    }
    
    public boolean eliminarDetalle(int id){
    
        return ordenDetalleDAO.eliminarDetalleOrden(id);   
    }
    
    

    public ArrayList<DetalleOrdenVM> listarDetalleOrden(int id) {

        try {

            ArrayList<DetalleOrden> listadoDetalleOrden = ordenDetalleDAO.listarDetalles(id);
            ArrayList<DetalleOrdenVM> lDetalleOrdenVM = new ArrayList<DetalleOrdenVM>();

            //listadoOrden = ordenDAO.listarOrdenes();
            for (DetalleOrden it : listadoDetalleOrden) {

                DetalleOrdenVM detalleOrdenVM = new DetalleOrdenVM();
                detalleOrdenVM.setIdDetalleOrden(it.getIdDetalleOrden());
                detalleOrdenVM.setNumArticulo(it.getNumArticulo());
                detalleOrdenVM.setCantidad(it.getCantidad());
                detalleOrdenVM.setUnidadMedida(it.getUnidadMedida());
                detalleOrdenVM.setDescripcionArticulo(it.getDescripcionArticulo());
                detalleOrdenVM.setPrecioUnitario(it.getPrecioUnitario());
                detalleOrdenVM.setPrecioTotal(it.getPrecioTotal());
                detalleOrdenVM.setOrdenId(it.getOrdenId());

                lDetalleOrdenVM.add(detalleOrdenVM);
            }

            return lDetalleOrdenVM;
        } catch (Exception e) {

            System.err.println(e.toString());

            return null;
        }
    }
    
    public boolean eliminarTotalDetalle(int id){
    
        return ordenDetalleDAO.eliminadoTotalDetalleOrden(id);
    }
}