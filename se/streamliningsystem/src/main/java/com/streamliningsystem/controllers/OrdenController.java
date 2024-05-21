package com.streamliningsystem.controllers;

import com.streamliningsystem.data.DAO.OrdenDAO;
import com.streamliningsystem.data.Models.Orden;
import com.streamliningsystem.models.ViewModels.OrdenVM;
import com.streamliningsystem.models.ViewModels.TablaOrdenVM;
import java.util.ArrayList;

public class OrdenController {

    // Objetos Globales
    OrdenDAO ordenDAO = new OrdenDAO();

    //Metodo de insercion de fechas
    public boolean guardarOrden(OrdenVM ordenVM) {

        Orden orden = new Orden();
        orden.setCodOrden(ordenVM.codOrden);
        orden.setEncargadoOrden(ordenVM.encargadoOrden);
        orden.setTotales(ordenVM.totales);
        orden.setLimite_cotizacion(ordenVM.getLimite_cotizacion());
        orden.setFecha_de_entrega(ordenVM.getFecha_de_entrega());
        orden.setHora_entrega_desde(ordenVM.getHora_entrega_desde());
        orden.setHora_entrega_hasta(ordenVM.getHora_entrega_hasta());
        orden.setTiempo_entrega(ordenVM.getTiempo_entrega());
        orden.setPlazo_entrega(ordenVM.getPlazo_entrega());
        orden.setLugar_entrega(ordenVM.getLugar_entrega());
        orden.setVigencia_de_la_cotizacion(ordenVM.getVigencia_de_la_cotizacion());
        orden.setTiempo_de_garantia(ordenVM.getTiempo_de_garantia());
        orden.setClienteId(ordenVM.clienteId);
        orden.setProveedorId(ordenVM.proveedorId);
        orden.setFechasErId(ordenVM.fechasErId);

        return ordenDAO.ingresarOrden(orden);
    }

    public boolean actualizarOrden(OrdenVM ordenVM) {

        Orden orden = new Orden();
        orden.setIdOrden(ordenVM.idOrden);
        orden.setCodOrden(ordenVM.codOrden);
        orden.setEncargadoOrden(ordenVM.encargadoOrden);
        orden.setTotales(ordenVM.totales);
        orden.setLimite_cotizacion(ordenVM.getLimite_cotizacion());
        orden.setFecha_de_entrega(ordenVM.getFecha_de_entrega());
        orden.setHora_entrega_desde(ordenVM.getHora_entrega_desde());
        orden.setHora_entrega_hasta(ordenVM.getHora_entrega_hasta());
        orden.setTiempo_entrega(ordenVM.getTiempo_entrega());
        orden.setPlazo_entrega(ordenVM.getPlazo_entrega());
        orden.setLugar_entrega(ordenVM.getLugar_entrega());
        orden.setVigencia_de_la_cotizacion(ordenVM.getVigencia_de_la_cotizacion());
        orden.setTiempo_de_garantia(ordenVM.getTiempo_de_garantia());
        orden.setClienteId(ordenVM.clienteId);
        orden.setProveedorId(ordenVM.proveedorId);
        orden.setFechasErId(ordenVM.fechasErId);

        return ordenDAO.actualizarOrden(orden);
    }

    public int obtenerOrden() {

        return ordenDAO.obtenerOrden();
    }

    public ArrayList<TablaOrdenVM> listarOrden() {

        return ordenDAO.listarOrdenes();
    }

    public TablaOrdenVM datosOrden(int id) {

        return ordenDAO.obtenerOrdenId(id);
    }

    public boolean eliminarOrden(int id) {

        return ordenDAO.eliminarOrden(id);
    }

}
