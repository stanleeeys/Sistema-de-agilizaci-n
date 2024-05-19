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
        orden.setClienteId(ordenVM.clienteId);
        orden.setProveedorId(ordenVM.proveedorId);
        orden.setFechasErId(ordenVM.fechasErId);

        return ordenDAO.ingresarOrden(orden);
    }

    public int obtenerOrden() {

        return ordenDAO.obtenerOrden();
    }

//    public ArrayList<OrdenVM> listarOrden() {
//
//        try {
//
//            ArrayList<Orden> listadoOrden = new ArrayList<Orden>();
//            ArrayList<OrdenVM> lOrdenVM = new ArrayList<OrdenVM>();
//
//            //listadoOrden = ordenDAO.listarOrdenes();
//
//            for (Orden it : listadoOrden) {
//
//                OrdenVM ordenVM = new OrdenVM();
//                ordenVM.setIdOrden(it.getIdOrden());
//                ordenVM.setCodOrden(it.getCodOrden());
//                ordenVM.setEncargadoOrden(it.getEncargadoOrden());
//                ordenVM.setTotales(it.getTotales());
//                ordenVM.setClienteId(it.getClienteId());
//                ordenVM.setProveedorId(it.getProveedorId());
//                ordenVM.setFechasErId(it.getFechasErId());
//                
//
//                lOrdenVM.add(ordenVM);
//            }
//
//            return lOrdenVM;
//        } catch (Exception e) {
//            
//            System.err.println(e.toString());
//
//            return null;
//        }
//    }
    public ArrayList<TablaOrdenVM> listarOrden() {

        return ordenDAO.listarOrdenes();
    }

    public TablaOrdenVM datosOrden(int id) {

        return ordenDAO.obtenerOrdenId(id);
    }

}
