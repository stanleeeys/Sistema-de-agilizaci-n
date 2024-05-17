package com.streamliningsystem.controllers;

import com.streamliningsystem.data.DAO.OrdeDetalleDAO;
import com.streamliningsystem.data.Models.DetalleOrden;
import com.streamliningsystem.models.ViewModels.DetalleOrdenVM;

public class DetalleOrdenController {
    
    // Objetos Globales
    OrdeDetalleDAO ordenDetalleDAO = new OrdeDetalleDAO();

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
    
}
